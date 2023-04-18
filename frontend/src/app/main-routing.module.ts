import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {StudentListComponent} from "./pages/student-list/student-list.component";
import {FrontpageComponent} from "./pages/frontpage/frontpage.component";
import {DetailsComponent} from "./pages/details/details.component";

const routes: Routes = [
  {
    path: '',
    component: FrontpageComponent
  },
  {
    path: 'list',
    component: StudentListComponent
  },
  {
    path: 'details/:jmbag',
    component: DetailsComponent
  }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
