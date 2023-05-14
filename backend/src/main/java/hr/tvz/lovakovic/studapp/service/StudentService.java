package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.model.Student;
import hr.tvz.lovakovic.studapp.model.StudentCommand;
import hr.tvz.lovakovic.studapp.model.StudentDTO;
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
