package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.model.Course;
import hr.tvz.lovakovic.studapp.model.CourseDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();
    List<CourseDTO> findCoursesByStudentJmbag(String jmbag);
    CourseDTO convertToCourseDTO(Course course);
}
