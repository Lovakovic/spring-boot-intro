import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {StudentService} from "../../../../service/student.service";
import Student from "../../../../model/student.model";

@Component({
  selector: 'app-new-student',
  templateUrl: './new-student.component.html',
  styleUrls: ['./new-student.component.css']
})
export class NewStudentComponent implements OnInit {
  addStudentForm: FormGroup;

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
          console.log('Student added successfully:', response);
        },
        (error) => {
          console.error('Error adding student:', error);
        }
      );
    } else {
      console.warn('Form is invalid');
    }
  }
}
