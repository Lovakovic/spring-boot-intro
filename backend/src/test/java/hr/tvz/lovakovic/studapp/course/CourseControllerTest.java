package hr.tvz.lovakovic.studapp.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.lovakovic.studapp.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CourseService courseService;

    @MockBean
    private JwtService jwtService;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getCourses_withNoParam_returnsAllCourses() throws Exception {
        CourseDTO course1 = new CourseDTO(1, "Course1", 6);
        CourseDTO course2 = new CourseDTO(2, "Course2", 5);
        List<CourseDTO> courseDTOs = Arrays.asList(course1, course2);

        when(courseService.findAll()).thenReturn(courseDTOs);

        mockMvc.perform(get("/course"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Course1")))
                .andExpect(jsonPath("$[0].ects", is(6)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Course2")))
                .andExpect(jsonPath("$[1].ects", is(5)));
    }

    @Test
    public void getCourses_withJmbag_returnsCoursesForJmbag() throws Exception {
        CourseDTO course1 = new CourseDTO(1, "Course1", 6);
        List<CourseDTO> courseDTOs = List.of(course1);

        when(courseService.findByStudents_Jmbag("1234567890")).thenReturn(courseDTOs);

        mockMvc.perform(get("/course?jmbag=1234567890"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Course1")))
                .andExpect(jsonPath("$[0].ects", is(6)));
    }
}
