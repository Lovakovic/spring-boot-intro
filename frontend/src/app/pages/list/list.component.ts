import {Component} from '@angular/core';
import {StudentService} from "../../service/student.service";
import Student from "../../model/student.model";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent {
  selectedStudent?: Student;
  jmbagFilter: string = '';

  constructor(
    public studentService: StudentService
  ) {}

  onStudentSelected(student: Student) {
    this.selectedStudent = student;
  }

  onFilterByJmbagChanged() {
    this.studentService.filterByJmbag(this.jmbagFilter);
  }

  onToggleSort() {
    this.studentService.toggleSort();
  }
}
