import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ObjectComponent } from './components/object/object.component';

const routes: Routes = [
  {
    path: "**",
    component: ObjectComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
