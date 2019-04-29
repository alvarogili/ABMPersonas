import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PersonaComponent } from './persona/persona.component';
import { TablaComponent } from './tabla/tabla.component';

const routes: Routes = [
  { path: '', component: TablaComponent },
  { path: 'nuevapersona', component: PersonaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
