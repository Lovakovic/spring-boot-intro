import {Component, Input} from '@angular/core';
import Student from "../../../../model/student.model";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent {

  @Input() student!: Student;
  protected readonly Number = Number;
}
