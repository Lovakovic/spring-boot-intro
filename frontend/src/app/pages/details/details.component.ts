import {Component, OnInit} from '@angular/core';
import Student from "../../model/student.model";
import {ActivatedRoute} from "@angular/router";
import {StudentService} from "../../service/student.service";
import {Observable} from "rxjs";
import Course from "../../model/course.model";
import {CourseService} from "../../service/course.service";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html'
})
export class DetailsComponent implements OnInit {
  student?: Student;
  courses$?: Observable<Course[]>;
  editMode: boolean = false;

  constructor(private route: ActivatedRoute,
              private studentService: StudentService,
              private courseService: CourseService
  ) {}

  ngOnInit(): void {
    let studentJmbag = this.route.snapshot.paramMap.get('jmbag') as string;
    this.studentService.getStudentByJmbag(studentJmbag).subscribe(_student => {
      this.student = _student;
    })

    this.courses$ = this.courseService.getCoursesByStudentJmbag(studentJmbag);
  }

  updateStudentInfo(updatedStudent: Student): void {
    this.student = updatedStudent;
    this.editMode = false;
  }

  protected readonly Number = Number;
}
