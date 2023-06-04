package hr.tvz.lovakovic.studapp.course;

import hr.tvz.lovakovic.studapp.course.Course;
import hr.tvz.lovakovic.studapp.course.CourseDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();
    CourseDTO convertToCourseDTO(Course course);
    List<CourseDTO> findByStudents_Jmbag(String jmbag);
}
