import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { StudentListComponent } from './pages/student-list/student-list.component';
import { DetailsComponent } from './pages/details/details.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MainRoutingModule} from "./main-routing.module";
import {RouterOutlet} from "@angular/router";
import { FrontpageComponent } from './pages/frontpage/frontpage.component';
import {HttpClientModule} from "@angular/common/http";
import { NewStudentComponent } from './pages/student-list/components/new-student/new-student.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentListComponent,
    DetailsComponent,
    FrontpageComponent,
    NewStudentComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MainRoutingModule,
    RouterOutlet,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
