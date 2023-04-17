package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.command.StudentCommand;
import hr.tvz.lovakovic.studapp.model.StudentDTO;
import hr.tvz.lovakovic.studapp.model.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAll();

    StudentDTO findStudentByJMBAG(String JMBAG);
    Boolean addStudent(StudentCommand studentCommand);
    Boolean deleteStudentByJMBAG(String JMBAG);
    StudentDTO convertToStudentDTO(Student student);
}
