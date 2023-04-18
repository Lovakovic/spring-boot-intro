import {Injectable} from '@angular/core';
import {BehaviorSubject, map, Observable, tap} from 'rxjs';
import Student from '../model/student.model';
import {HttpClient} from "@angular/common/http";

const API_URL = `http://localhost:8080/api/students`;

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private studentsSubject = new BehaviorSubject<Student[]>([]);
  students$: Observable<Student[]> = this.studentsSubject.asObservable();

  private allStudents: Student[] = [];

  sortOrder: string = 'asc';

  constructor(private http: HttpClient) {
    this.getStudents().subscribe((students) => {
      this.studentsSubject.next(students);
      this.allStudents = students;
    });
  }

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(API_URL);
  }

  getStudentByJmbag(jmbag: string): Observable<Student> {
    return this.http.get<Student>(API_URL + `/${jmbag}`);
  }

  getLocalStudentByJmbag(jmbag: string): Observable<Student | null> {
    return this.students$.pipe(
      map(students => students.find(student => student.jmbag === jmbag) || null)
    );
  }

  postStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(API_URL, student);
  }

  deleteStudent(jmbag: string): Observable<void> {
    return this.http.delete<void>(`${API_URL}/${jmbag}`).pipe(
      tap(() => {
        this.studentsSubject.next(
          this.studentsSubject.getValue().filter(student => student.jmbag !== jmbag)
        );
      })
    );
  }

  filterByJmbag(jmbag: string) {
    const students = this.studentsSubject.getValue();

    if (jmbag !== '') {
      this.studentsSubject.next(students.filter((student) => student.jmbag.startsWith(jmbag)));
    } else {
      this.studentsSubject.next(students);
    }
  }

  toggleSort() {
    const students = [...this.allStudents];

    if (this.sortOrder === 'desc') {
      students.sort((studentA, studentB) => {
        if (studentA.ectsPoints < studentB.ectsPoints) {
          return -1;
        } else if (studentA.ectsPoints > studentB.ectsPoints) {
          return 1;
        } else {
          return 0;
        }
      });
      this.sortOrder = 'asc';
    } else {
      students.sort((studentA, studentB) => {
        if (studentA.ectsPoints < studentB.ectsPoints) {
          return 1;
        } else if (studentA.ectsPoints > studentB.ectsPoints) {
          return -1;
        } else {
          return 0;
        }
      });
      this.sortOrder = 'desc';
    }
    this.studentsSubject.next(students);
  }
}
