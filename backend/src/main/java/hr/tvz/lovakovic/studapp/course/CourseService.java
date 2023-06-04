package hr.tvz.lovakovic.studapp.course;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();
    CourseDTO convertToCourseDTO(Course course);
    List<CourseDTO> findByStudents_Jmbag(String jmbag);
}
