package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();
    Optional<Student> findStudentByJMBAG(String JMBAG);
    Student save(Student student);
    Student replace(String jmbag, Student newStudent);
    Boolean deleteByJmbag(String jmbag);
}
