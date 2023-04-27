package hr.tvz.lovakovic.studapp.controller;

import hr.tvz.lovakovic.studapp.exception.StudentAlreadyExistsException;
import hr.tvz.lovakovic.studapp.model.DetailStudentDTO;
import hr.tvz.lovakovic.studapp.model.StudentCommand;
import hr.tvz.lovakovic.studapp.model.StudentDTO;
import hr.tvz.lovakovic.studapp.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
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

    @GetMapping("/detail")
    public List<DetailStudentDTO> getAllDetailStudents() { return studentService.findAllDetail(); };

    @GetMapping("/{JMBAG}")
    public ResponseEntity<StudentDTO> getStudentByJMBAG(@PathVariable String JMBAG) {
        StudentDTO studentDTO = studentService.findStudentByJMBAG(JMBAG);
        if (studentDTO != null) {
            return ResponseEntity.ok(studentDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/detail/{JMBAG}")
    public ResponseEntity<DetailStudentDTO> getDetailStudentByJMBAG(@PathVariable String JMBAG) {
        DetailStudentDTO detailStudentDTO = studentService.findDetailStudentByJMBAG(JMBAG);
        if (detailStudentDTO != null) {
            return ResponseEntity.ok(detailStudentDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<DetailStudentDTO> addStudent(@Valid @RequestBody StudentCommand studentCommand) {
        DetailStudentDTO detailStudentDTO = studentService.addStudent(studentCommand);
        return new ResponseEntity<>(detailStudentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{JMBAG}")
    public ResponseEntity<DetailStudentDTO> putStudent(@PathVariable String JMBAG,
                                                 @Valid @RequestBody StudentCommand studentCommand) {
        Optional<DetailStudentDTO> updatedStudent = studentService.putStudent(JMBAG, studentCommand);

        return updatedStudent.map(detailStudentDTO -> new ResponseEntity<>(detailStudentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.CREATED));
    }

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<Void> handleStudentAlreadyExistsException(StudentAlreadyExistsException ex) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
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