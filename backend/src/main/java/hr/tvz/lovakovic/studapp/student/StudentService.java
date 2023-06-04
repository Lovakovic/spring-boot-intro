package hr.tvz.lovakovic.studapp.student;

import hr.tvz.lovakovic.studapp.model.StudentCommand;
import org.springframework.data.util.Pair;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAll();
    StudentDTO findStudentByJMBAG(String JMBAG);
    StudentDTO addStudent(StudentCommand studentCommand);
    Pair<Boolean, StudentDTO> putStudent(String jmbag, StudentCommand studentCommand);
    Boolean deleteStudentByJMBAG(String JMBAG);
    StudentDTO convertToStudentDTO(Student student);
}
