package hr.tvz.lovakovic.studapp.controller;

import hr.tvz.lovakovic.studapp.command.StudentCommand;
import hr.tvz.lovakovic.studapp.model.StudentDTO;
import hr.tvz.lovakovic.studapp.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{JMBAG}")
    public ResponseEntity<StudentDTO> getStudentByJMBAG(@PathVariable String JMBAG) {
        StudentDTO studentDTO = studentService.findStudentByJMBAG(JMBAG);
        if (studentDTO != null) {
            return ResponseEntity.ok(studentDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addStudent(@Valid @RequestBody StudentCommand studentCommand) {
        boolean added = studentService.addStudent(studentCommand);
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{JMBAG}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String JMBAG) {
        boolean deleted = studentService.deleteStudentByJMBAG(JMBAG);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}