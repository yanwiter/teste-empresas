import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PersonagensApiService } from '../shared/personagens-api.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-personagens',
  templateUrl: './personagens.component.html',
  styleUrls: ['./personagens.component.css']
})

export class PersonagensComponent implements OnInit {

  public displayedColumns: string[] = ['imagem', 'series', 'eventos'];
  public dataSource = new MatTableDataSource();
  public dataSourceWithPageSize: MatTableDataSource<any>;
  public totalLength = 0;
  public pageSize = 10;
  public filterValue = "";

  constructor(
    private personagensService: PersonagensApiService,
    public dialog: MatDialog) {
  }

  @ViewChild('paginator')
    paginator: MatPaginator;

  @ViewChild('paginatorPageSize')
    paginatorPageSize: MatPaginator;

  @ViewChild(MatSort)
    sort: MatSort;

  pageSizes = [5, 10, 20];

  openDialog() {
    //this.dialog.open(#dialog);
  }

  getPersonagens(offset: number) {
    this.personagensService.getTodosPersonagens(this.pageSize, offset, this.filterValue).
      subscribe(data => {
        this.dataSource = new MatTableDataSource(data.results);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.totalLength = data.total;
      });
  }

  ngOnInit(): void {
    this.getPersonagens(0);
  }

  applyFilter(event: Event) {
    this.filterValue = (event.target as HTMLInputElement).value.trim().toLowerCase();
    this.getPersonagens(0);
  }

  public getData(event) {
    this.pageSize = event.pageSize;
    const skip = event.pageIndex * this.pageSize;
    this.getPersonagens(skip);
  }

}
