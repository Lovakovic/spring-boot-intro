package hr.tvz.lovakovic.studapp.mapper;

import hr.tvz.lovakovic.studapp.model.Course;
import hr.tvz.lovakovic.studapp.model.CourseDTO;

public class CourseMapper {
    public static CourseDTO toDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setEcts(course.getEcts());

        return courseDTO;
    }
}
