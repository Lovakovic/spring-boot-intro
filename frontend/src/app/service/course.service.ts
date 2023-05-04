import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import Course from "../model/course.model";

const API_URL = `http://localhost:8080/api/courses`;

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http: HttpClient) { }

  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(API_URL);
  }

  getCoursesByStudentJmbag(jbmag: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${API_URL}/student/${jbmag}`);
  }
}
