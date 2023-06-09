package hr.tvz.lovakovic.studapp.student;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
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
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentCommand studentCommand) {
        StudentDTO detailStudentDTO = studentService.addStudent(studentCommand);
        return new ResponseEntity<>(detailStudentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{jmbag}")
    public ResponseEntity<StudentDTO> putStudent(@PathVariable String jmbag,
                                                       @Valid @RequestBody StudentCommand studentCommand) {
        Pair<Boolean, StudentDTO> result = studentService.putStudent(jmbag, studentCommand);
        Boolean isCreated = result.getFirst();
        StudentDTO updatedStudent = result.getSecond();

        if (isCreated) {
            return new ResponseEntity<>(updatedStudent, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        }
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