package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.model.DetailStudentDTO;
import hr.tvz.lovakovic.studapp.model.Student;
import hr.tvz.lovakovic.studapp.model.StudentCommand;
import hr.tvz.lovakovic.studapp.model.StudentDTO;
import org.springframework.data.util.Pair;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAll();
    List<DetailStudentDTO> findAllDetail();
    StudentDTO findStudentByJMBAG(String JMBAG);
    DetailStudentDTO findDetailStudentByJMBAG(String JMBAG);
    DetailStudentDTO addStudent(StudentCommand studentCommand);
    Pair<Boolean, DetailStudentDTO> putStudent(String jmbag, StudentCommand studentCommand);
    Boolean deleteStudentByJMBAG(String JMBAG);
    StudentDTO convertToStudentDTO(Student student);
    DetailStudentDTO convertToDetailStudentDTO(Student student);
}
