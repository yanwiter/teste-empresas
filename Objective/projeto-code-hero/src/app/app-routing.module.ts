import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonagensComponent } from './personagens/personagens.component';


const routes: Routes = [
/*   {
    path: '', redirectTo: '/personagens', pathMatch: 'full'
  },
  {
    path: 'personagens', component: PersonagensComponent
  }, */
  {
    path: '', redirectTo: '/personagens', pathMatch: 'full'
  },
  {
    path: 'personagens', component: PersonagensComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
