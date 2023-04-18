import {Component, OnInit} from '@angular/core';
import {StudentService} from "../../service/student.service";
import Student from "../../model/student.model";

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {
  jmbagFilter: string = '';
  sortOrder: string = 'asc';
  students: Student[] = [];
  displayStudents: Student[] = [];

  constructor(public studentService: StudentService) {}

  ngOnInit(): void {
    this.studentService.getStudents().subscribe((_students) => {
      this.students = _students;
      this.displayStudents = [...this.students];
    });
  }

  onFilterByJmbagChanged() {
    this.filterByJmbag(this.jmbagFilter);
  }

  filterByJmbag(jmbag: string) {
    if (jmbag !== '') {
      this.displayStudents = this.students.filter((student) => student.jmbag.startsWith(jmbag));
    } else {
      this.displayStudents = [...this.students];
    }
  }

  onToggleSort() {
    this.displayStudents = [...this.displayStudents].sort((studentA, studentB) => {
      if (this.sortOrder === 'desc') {
        return studentA.ectsPoints - studentB.ectsPoints;
      } else {
        return studentB.ectsPoints - studentA.ectsPoints;
      }
    });
    this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';
  }

  onDeleteStudent(jmbag: string): void {
    this.studentService.deleteStudent(jmbag).subscribe(() => {
      this.students = this.students.filter(student => student.jmbag !== jmbag);
      this.displayStudents = [...this.students];
    });
  }
}
