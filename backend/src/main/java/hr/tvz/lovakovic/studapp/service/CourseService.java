package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.model.Course;
import hr.tvz.lovakovic.studapp.model.CourseDTO;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();
    List<CourseDTO> findCoursesByStudentJmbag(String jmbag);
    CourseDTO convertToCourseDTO(Course course);

    CourseDTO findFirstCourseByName(String name);
    List<CourseDTO> findTop3CoursesByEcts();
    List<CourseDTO> findDistinctCoursesByName(String name);
    List<CourseDTO> findCoursesByNameEquals(String name);
    List<CourseDTO> findCoursesByNameIs(String name);
    List<CourseDTO> findCoursesByNameNot(String name);
    List<CourseDTO> findCoursesByIdIn(List<Long> ids);
    List<CourseDTO> findCoursesByIdNotIn(List<Long> ids);
    List<CourseDTO> findCoursesByStudentsDateOfBirthBefore(LocalDate date);
    List<CourseDTO> findCoursesByStudentsDateOfBirthAfter(LocalDate date);
    List<CourseDTO> findCoursesByStudentsDateOfBirthBetween(LocalDate startDate, LocalDate endDate);
    List<CourseDTO> findCoursesByEctsValue(Integer ects);
}
