package hr.tvz.lovakovic.studapp.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hr.tvz.lovakovic.studapp.security.JwtService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private JwtService jwtService;

    private StudentDTO studentDTO;
    private StudentCommand studentCommand;

    @Before
    public void setUp() {
        studentDTO = new StudentDTO();
        studentDTO.setJmbag("0000000000");
        studentDTO.setFirstName("John");
        studentDTO.setLastName("Doe");
        studentDTO.setDateOfBirth("2000-01-01");
        studentDTO.setEctsPoints(120);
        studentDTO.setTuitionShouldBePaid(false);
        studentDTO.setEnrolledStudiesAtYear(2022);
        studentDTO.setCurrentSemester(2);

        studentCommand = new StudentCommand();
        studentCommand.setJmbag("0000000000");
        studentCommand.setFirstName("John");
        studentCommand.setLastName("Doe");
        studentCommand.setDateOfBirth(LocalDate.of(2000, 1, 1));
        studentCommand.setEctsPoints(120);
    }

    @Test
    @WithMockUser(username = "user1")
    public void testGetAllStudents() throws Exception {
        when(studentService.findAll()).thenReturn(Collections.singletonList(studentDTO));

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].jmbag", is(studentDTO.getJmbag())));
    }

    @Test
    @WithMockUser(username = "user1")
    public void testGetStudentByJMBAG() throws Exception {
        when(studentService.findStudentByJMBAG(anyString())).thenReturn(studentDTO);

        mockMvc.perform(get("/student/{JMBAG}", studentDTO.getJmbag()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jmbag", is(studentDTO.getJmbag())));
    }

    @Test
    @WithMockUser(username = "user1")
    public void testAddStudent() throws Exception {
        when(studentService.addStudent(any(StudentCommand.class))).thenReturn(studentDTO);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(studentCommand)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user1")
    public void testPutStudent() throws Exception {
        when(studentService.putStudent(anyString(), any(StudentCommand.class))).thenReturn(Pair.of(true, studentDTO));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        mockMvc.perform(put("/student/{jmbag}", studentDTO.getJmbag())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(studentCommand)))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void testDeleteStudent() throws Exception {
        when(studentService.deleteStudentByJMBAG(anyString())).thenReturn(true);

        mockMvc.perform(delete("/student/{JMBAG}", studentDTO.getJmbag()))
                .andExpect(status().isForbidden());
    }
}
