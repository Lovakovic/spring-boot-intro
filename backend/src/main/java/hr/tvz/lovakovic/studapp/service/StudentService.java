package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.model.DetailStudentDTO;
import hr.tvz.lovakovic.studapp.model.Student;
import hr.tvz.lovakovic.studapp.model.StudentCommand;
import hr.tvz.lovakovic.studapp.model.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDTO> findAll();
    List<DetailStudentDTO> findAllDetail();
    StudentDTO findStudentByJMBAG(String JMBAG);
    DetailStudentDTO findDetailStudentByJMBAG(String JMBAG);
    StudentDTO addStudent(StudentCommand studentCommand);
    Optional<StudentDTO> putStudent(String jmbag, StudentCommand studentCommand);
    Boolean deleteStudentByJMBAG(String JMBAG);
    StudentDTO convertToStudentDTO(Student student);
    DetailStudentDTO convertToDetailStudentDTO(Student student);
}
