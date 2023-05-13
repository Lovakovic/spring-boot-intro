package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.mapper.CourseMapper;
import hr.tvz.lovakovic.studapp.model.Course;
import hr.tvz.lovakovic.studapp.model.CourseDTO;
import hr.tvz.lovakovic.studapp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> findCoursesByStudentJmbag(String jmbag) {
        return courseRepository.findByStudents_Jmbag(jmbag).stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public CourseDTO convertToCourseDTO(Course course) {
        return CourseMapper.toDTO(course);
    }

    @Override
    public CourseDTO findFirstCourseByName(String name) {
        return convertToCourseDTO(courseRepository.findFirstByName(name));
    }

    @Override
    public List<CourseDTO> findTop3CoursesByEcts() {
        return courseRepository.findTop3ByOrderByEctsDesc().stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> findDistinctCoursesByName(String name) {
        return courseRepository.findDistinctByName(name).stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> findCoursesByNameEquals(String name) {
        return courseRepository.findByNameEquals(name).stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> findCoursesByNameIs(String name) {
        return courseRepository.findByNameIs(name).stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> findCoursesByNameNot(String name) {
        return courseRepository.findByNameNot(name).stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> findCoursesByIdIn(List<Long> ids) {
        return courseRepository.findByIdIn(ids).stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> findCoursesByIdNotIn(List<Long> ids) {
        return courseRepository.findByIdNotIn(ids).stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> findCoursesByStudentsDateOfBirthBefore(LocalDate date) {
        return courseRepository.findByStudents_DateOfBirthBefore(date).stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> findCoursesByStudentsDateOfBirthAfter(LocalDate date) {
        return courseRepository.findByStudents_DateOfBirthAfter(date).stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> findCoursesByStudentsDateOfBirthBetween(LocalDate startDate, LocalDate endDate) {
        return courseRepository.findByStudents_DateOfBirthBetween(startDate, endDate).stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> findCoursesByEctsValue(Integer ects) {
        return courseRepository.findByEctsValue(ects).stream()
                .map(this::convertToCourseDTO)
                .toList();
    }
}
