package hr.tvz.lovakovic.studapp.audit;

import hr.tvz.lovakovic.studapp.security.JwtService;
import hr.tvz.lovakovic.studapp.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginRecordController.class)
public class LoginRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginRecordService loginRecordService;

    @MockBean
    private JwtService jwtService;

    private LoginRecordDTO loginRecord;

    @Before
    public void setUp() {
        loginRecord = new LoginRecordDTO();
        loginRecord.setId(1L);
        loginRecord.setUsername("user1");
        loginRecord.setRoleName("ROLE_USER");
        loginRecord.setDateTimeLogin(LocalDateTime.now());
        loginRecord.setDateTimeLogoff(LocalDateTime.now().plusHours(1));
    }

    @Test
    @WithMockUser(username = "user1")
    public void testGetAllLogins() throws Exception {
        List<LoginRecordDTO> loginRecords =  Collections.singletonList(loginRecord);
        when(loginRecordService.getAllLogins()).thenReturn(loginRecords);

        mockMvc.perform(get("/api/login"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(loginRecord.getId().intValue())));
    }

    @Test
    @WithMockUser(username = "user1")
    public void testGetLoginById() throws Exception {
        when(loginRecordService.getLoginById(anyLong())).thenReturn(Optional.of(loginRecord));

        mockMvc.perform(get("/api/login/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(loginRecord.getId().intValue())));
    }

    @Test
    @WithMockUser(username = "user1")
    public void testGetLoginByIdNotFound() throws Exception {
        when(loginRecordService.getLoginById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/login/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user1")
    public void testGetLastLoginByUser() throws Exception {
        when(loginRecordService.getLastLoginByUser(anyLong())).thenReturn(Optional.of(loginRecord));

        mockMvc.perform(get("/api/login/lastLogin/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(loginRecord.getId().intValue())));
    }

    @Test
    @WithMockUser(username = "user1")
    public void testGetLastLoginByUserNotFound() throws Exception {
        when(loginRecordService.getLastLoginByUser(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/login/lastLogin/1"))
                .andExpect(status().isNotFound());
    }
}