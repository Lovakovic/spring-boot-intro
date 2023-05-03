package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.mapper.CourseMapper;
import hr.tvz.lovakovic.studapp.model.Course;
import hr.tvz.lovakovic.studapp.model.CourseDTO;
import hr.tvz.lovakovic.studapp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
