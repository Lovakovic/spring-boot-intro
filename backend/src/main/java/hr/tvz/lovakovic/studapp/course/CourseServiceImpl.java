package hr.tvz.lovakovic.studapp.course;

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
    public List<CourseDTO> findByStudents_Jmbag(String jmbag) {
        return courseRepository.findByStudents_Jmbag(jmbag).stream()
                .map(this::convertToCourseDTO)
                .toList();
    }

    @Override
    public CourseDTO convertToCourseDTO(Course course) {
        return CourseMapper.toDTO(course);
    }
}
