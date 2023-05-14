package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.model.Course;
import hr.tvz.lovakovic.studapp.model.CourseDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();
    CourseDTO convertToCourseDTO(Course course);
    List<CourseDTO> findByStudents_Jmbag(String jmbag);
}
