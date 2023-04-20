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
  @Input() jmbag?: string;

  // Notification-related
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
      dateOfBirth: ['', Validators.required],
      ectsPoints: ['', Validators.required],
      enrolledStudiesAtYear: ['', Validators.required],
      currentSemester: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    if(this.jmbag) {
      this.studentService.getStudentByJmbag(this.jmbag).subscribe(student => {
        this.addStudentForm.patchValue(student);
      })
    }
  }

  onSubmit(): void {
    if (this.addStudentForm.valid) {
      const newStudent: Student = {
        ...this.addStudentForm.value
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
