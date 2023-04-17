import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import Student from '../model/student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  students: Array<Student> = [
    {
      jmbag: '8724873213',
      ectsPoints: 74,
      shouldPayTuition: true,
      fullName: 'Marko Markovic',
      enrolledAtYear: 2019,
      currentSemester: 3
    },
    {
      jmbag: '2734236456',
      ectsPoints: 170,
      shouldPayTuition: false,
      fullName: 'Josipa Jovic',
      enrolledAtYear: 2020,
      currentSemester: 2
    },
    {
      jmbag: '9832759720',
      ectsPoints: 150,
      shouldPayTuition: false,
      fullName: 'Ivo Ivkovic',
      enrolledAtYear: 2015,
      currentSemester: 1
    },
    {
      jmbag: '9243795741',
      ectsPoints: 169,
      shouldPayTuition: false,
      fullName: 'Marija Marinovic',
      enrolledAtYear: 2020,
      currentSemester: 2
    }
  ];

  sortOrder: string = 'asc';
  displayStudents: Array<Student> = this.students;

  constructor() { }

  getStudents(): Observable<Student[]> {
    return of(this.displayStudents);
  }

  filterByJmbag(jmbag: string) {
    if(jmbag !== '') {
      this.displayStudents = this.students.filter(student => student.jmbag.startsWith(jmbag));
    } else {
      this.displayStudents = this.students;
    }
  }

  toggleSort() {
    if(this.sortOrder === 'desc') {
      this.students.sort((studentA, studentB) => {
        if (studentA.ectsPoints < studentB.ectsPoints) {
          return -1;
        } else if (studentA.ectsPoints > studentB.ectsPoints) {
          return 1;
        } else {
          return 0;
        }
      })
      this.sortOrder = 'asc';
    } else {
      this.students.sort((studentA, studentB) => {
        if (studentA.ectsPoints < studentB.ectsPoints) {
          return 1;
        } else if (studentA.ectsPoints > studentB.ectsPoints) {
          return -1;
        } else {
          return 0;
        }
      })
      this.sortOrder = 'desc';
    }
  }
}
