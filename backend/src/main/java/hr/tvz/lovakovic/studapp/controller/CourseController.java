package hr.tvz.lovakovic.studapp.controller;

import hr.tvz.lovakovic.studapp.model.CourseDTO;
import hr.tvz.lovakovic.studapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "*")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> getCourses(@RequestParam(value = "jmbag", required = false) String jmbag) {
        if (jmbag != null) {
            return courseService.findByStudents_Jmbag(jmbag);
        } else {
            return courseService.findAll();
        }
    }

    @GetMapping("/student/{jmbag}")
    public List<CourseDTO> getCoursesByStudentJmbag(@PathVariable String jmbag) {
        return courseService.findCoursesByStudentJmbag(jmbag);
    }
}
