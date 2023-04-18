import {Component, OnInit} from '@angular/core';
import {StudentService} from "../../service/student.service";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";
import Student from "../../model/student.model";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html'
})
export class DetailsComponent implements OnInit {
  jmbag!: string;
  student$!: Observable<Student | null>;

  constructor(
    public studentService: StudentService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.jmbag = this.route.snapshot.paramMap.get('jmbag') as string;
    this.student$ = this.studentService.getLocalStudentByJmbag(this.jmbag);
  }

  protected readonly Number = Number;
}
