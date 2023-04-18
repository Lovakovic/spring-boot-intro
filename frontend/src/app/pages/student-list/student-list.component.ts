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
  students: Student[] = [];

  constructor(public studentService: StudentService) {}

  ngOnInit(): void {
    // Subscribe to students$ and update the local students array when it changes
    this.studentService.students$.subscribe((students) => {
      this.students = students;
    });
  }

  onFilterByJmbagChanged() {
    this.studentService.filterByJmbag(this.jmbagFilter);
  }

  onToggleSort() {
    this.studentService.toggleSort();
  }

  onDeleteStudent(jmbag: string): void {
    this.studentService.deleteStudent(jmbag).subscribe();
  }
}
