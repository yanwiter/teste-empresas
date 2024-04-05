import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class PersonagensApiService {

  PUBLIC_KEY = 'c895e6ab3575ef99eb581cd70654e147';
  HASH = '';

  /*URL_API = `https://gateway.marvel.com:443/v1/public/characters?ts=1&orderBy=name&limit=10&apikey=c895e6ab3575ef99eb581cd70654e147=${this.HASH}`;*/
  /*URL_API = `https://gateway.marvel.com:443/v1/public/characters?ts=1&apikey=${this.PUBLIC_KEY}&hash=${this.HASH}`; */

 URL_API = `https://gateway.marvel.com:443/v1/public/characters?orderBy=name&apikey=ed5accb8c7697714ac1dbf1c7584c3c0`;

  //chave publica c895e6ab3575ef99eb581cd70654e147
  //chave privada   0c746ba43cf97dc774597a0b88d46d6ac3c26584

  constructor(private http: HttpClient) { }

   getTodosPersonagens(limit: number, offset: number, nameStartsWith: string): Observable<any> {
     var url: string = this.URL_API + "&limit=" + limit + "&offset=" + offset;
     if(nameStartsWith.length > 0){
      url = url + "&nameStartsWith=" + nameStartsWith
     }
     return this.http.get<any>(url)
     .pipe(map((data: any) => data.data))
   }
}
