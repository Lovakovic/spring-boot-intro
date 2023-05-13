package hr.tvz.lovakovic.studapp.controller;

import hr.tvz.lovakovic.studapp.model.CourseDTO;
import hr.tvz.lovakovic.studapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/student/{jmbag}")
    public List<CourseDTO> getCoursesByStudentJmbag(@PathVariable String jmbag) {
        return courseService.findCoursesByStudentJmbag(jmbag);
    }

    @GetMapping("/first/name/{name}")
    public CourseDTO getFirstCourseByName(@PathVariable String name) {
        return courseService.findFirstCourseByName(name);
    }

    @GetMapping("/top3/ects")
    public List<CourseDTO> getTop3CoursesByEcts() {
        return courseService.findTop3CoursesByEcts();
    }

    @GetMapping("/distinct/name/{name}")
    public List<CourseDTO> getDistinctCoursesByName(@PathVariable String name) {
        return courseService.findDistinctCoursesByName(name);
    }

    @GetMapping("/name/equals/{name}")
    public List<CourseDTO> getCoursesByNameEquals(@PathVariable String name) {
        return courseService.findCoursesByNameEquals(name);
    }

    @GetMapping("/name/is/{name}")
    public List<CourseDTO> getCoursesByNameIs(@PathVariable String name) {
        return courseService.findCoursesByNameIs(name);
    }

    @GetMapping("/name/not/{name}")
    public List<CourseDTO> getCoursesByNameNot(@PathVariable String name) {
        return courseService.findCoursesByNameNot(name);
    }

    @PostMapping("/id/in")
    public List<CourseDTO> getCoursesByIdIn(@RequestBody List<Long> ids) {
        return courseService.findCoursesByIdIn(ids);
    }

    @PostMapping("/id/notin")
    public List<CourseDTO> getCoursesByIdNotIn(@RequestBody List<Long> ids) {
        return courseService.findCoursesByIdNotIn(ids);
    }

    @GetMapping("/students/dob/before/{date}")
    public List<CourseDTO> getCoursesByStudentsDateOfBirthBefore(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return courseService.findCoursesByStudentsDateOfBirthBefore(date);
    }

    @GetMapping("/students/dob/after/{date}")
    public List<CourseDTO> getCoursesByStudentsDateOfBirthAfter(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return courseService.findCoursesByStudentsDateOfBirthAfter(date);
    }

    @GetMapping("/students/dob/between/{startDate}/{endDate}")
    public List<CourseDTO> getCoursesByStudentsDateOfBirthBetween(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return courseService.findCoursesByStudentsDateOfBirthBetween(startDate, endDate);
    }

    @GetMapping("/ects/{ects}")
    public List<CourseDTO> getCoursesByEctsValue(@PathVariable Integer ects) {
        return courseService.findCoursesByEctsValue(ects);
    }
}
