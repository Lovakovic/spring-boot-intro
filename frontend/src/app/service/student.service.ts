import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import Student from '../model/student.model';
import {HttpClient} from "@angular/common/http";

const API_URL = `http://localhost:8080/api/students`;

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  constructor(private http: HttpClient) {}

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(API_URL);
  }

  getStudentByJmbag(jmbag: string): Observable<Student> {
    return this.http.get<Student>(`${API_URL}/${jmbag}`);
  }

  postStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(API_URL, student);
  }

  deleteStudent(jmbag: string) {
    return this.http.delete(`${API_URL}/${jmbag}`);
  }
}
