import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {StudentService} from "../../service/student.service";
import Student from "../../model/student.model";

@Component({
  selector: 'app-edit-student',
  templateUrl: './edit-student.component.html',
  styleUrls: ['./edit-student.component.css']
})
export class EditStudentComponent implements OnInit {
  @Input() title! : string;
  addStudentForm: FormGroup;

  notificationMessage: string = '';
  timeoutId: any;

  constructor(
    private formBuilder: FormBuilder,
    private studentService: StudentService
  ) {
    this.addStudentForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      jmbag: [
        '',
        [Validators.required, Validators.minLength(10), Validators.maxLength(10)],
      ],
      ectsPoints: ['', Validators.required],
      enrolledStudiesAtYear: ['', Validators.required],
      currentSemester: ['', Validators.required],
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.addStudentForm.valid) {
      const newStudent: Student = {
        ...this.addStudentForm.value,
        dateOfBirth: '2000-01-01',
      };
      this.studentService.postStudent(newStudent).subscribe(
        (response) => {
          this.notificationMessage = 'Student added successfully.';
          this.clearNotifcationMessage();
        },
        (error) => {
          this.notificationMessage = 'Error adding student.';
          console.log(error);
          this.clearNotifcationMessage();
        }
      );
    } else {
      this.notificationMessage = 'Form is invalid.';
      console.warn('Form is invalid');
      this.clearNotifcationMessage();
    }
  }

  clearNotifcationMessage(): void {
    clearTimeout(this.timeoutId);
    this.timeoutId = setTimeout(() => {
      this.notificationMessage = '';
    }, 3000);
  }
}
