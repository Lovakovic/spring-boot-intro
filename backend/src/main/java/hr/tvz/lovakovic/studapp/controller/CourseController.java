package hr.tvz.lovakovic.studapp.controller;

import hr.tvz.lovakovic.studapp.model.CourseDTO;
import hr.tvz.lovakovic.studapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping("/students/{jmbag}")
    public List<CourseDTO> getCoursesByStudentJmbag(@PathVariable String jmbag) {
        return courseService.findCoursesByStudentJmbag(jmbag);
    }
}
