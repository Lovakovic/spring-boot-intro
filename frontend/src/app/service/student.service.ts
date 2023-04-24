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

  getStudents(detailed: boolean = false): Observable<Student[]> {
    if(detailed)
      return this.http.get<Student[]>(`${API_URL}/detail`);

    return this.http.get<Student[]>(`${API_URL}`);
  }

  getStudentByJmbag(jmbag: string, detailed: boolean = true): Observable<Student> {
    if(detailed)
      return this.http.get<Student>(`${API_URL}/detail/${jmbag}`);

    return this.http.get<Student>(`${API_URL}/${jmbag}`);
  }

  postStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(API_URL, student);
  }

  putStudent(student: Student): Observable<Student> {
    return this.http.put<Student>(`${API_URL}/${student.jmbag}`, student);
  }

  deleteStudent(jmbag: string) {
    return this.http.delete(`${API_URL}/${jmbag}`);
  }
}
