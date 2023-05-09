import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArtiklComponent } from './components/artikl/artikl.component';
import { DobavljacComponent } from './components/dobavljac/dobavljac.component';
import { PorudzbinaComponent } from './components/porudzbina/porudzbina.component';

const routes: Routes = [ { path: 'artikl', component: ArtiklComponent },   
{ path: 'dobavljac', component: DobavljacComponent },
{ path: 'porudzbina', component: PorudzbinaComponent },
{ path: '', redirectTo: '/artikl', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }