package hr.tvz.lovakovic.studapp.course;

public class CourseMapper {
    public static CourseDTO toDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setEcts(course.getEcts());

        return courseDTO;
    }
}
