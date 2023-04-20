import {Component, OnInit} from '@angular/core';
import Student from "../../model/student.model";
import {ActivatedRoute} from "@angular/router";
import {StudentService} from "../../service/student.service";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html'
})
export class DetailsComponent implements OnInit {
  student?: Student;
  editMode: boolean = false;

  constructor(private route: ActivatedRoute,
              private studentService: StudentService) {}

  ngOnInit(): void {
    let studentJmbag = this.route.snapshot.paramMap.get('jmbag') as string;
    this.studentService.getStudentByJmbag(studentJmbag).subscribe(_student => {
      this.student = _student;
    })
  }


  protected readonly Number = Number;
}
