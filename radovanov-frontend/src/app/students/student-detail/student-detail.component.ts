import { Component, OnInit, Input } from '@angular/core';
import { Student } from '../student.model';
import { ActivatedRoute } from '@angular/router';
import { StudentService } from '../student.service';
import { CourseService } from '../../course/course.service';
import { Course } from '../../course/course.model';
import { switchMap } from 'rxjs/operators';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html'
})
export class StudentDetailComponent implements OnInit {
  @Input() student: Student;
  courses: Course[];
  currentLang: string;

  constructor(
    private route: ActivatedRoute,
    private studentService: StudentService,
    private courseService: CourseService,
    private translateService: TranslateService
  ) { }

  ngOnInit(): void {
    this.currentLang = this.translateService.currentLang || this.translateService.getDefaultLang();

    this.translateService.onDefaultLangChange.subscribe((event) => {
      this.currentLang = event.lang;
    });

    this.translateService.onLangChange.subscribe((event) => {
      this.currentLang = event.lang;
    });

    this.route.paramMap.pipe(
      switchMap(params => {
        const jmbag = params.get('jmbag');
        return this.studentService.getStudent(jmbag);
      })
    ).subscribe((student: Student) => {
      console.log(student.aboutMe);
      this.student = student;
      this.courseService.getCoursesByJmbag(student.jmbag).subscribe(
        courses => this.courses = courses
      );
    });
  }
}
