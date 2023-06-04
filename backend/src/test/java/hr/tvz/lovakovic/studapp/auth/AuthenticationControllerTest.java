package hr.tvz.lovakovic.studapp.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.lovakovic.studapp.security.JwtService;
import hr.tvz.lovakovic.studapp.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtService jwtService;

    @Test
    public void shouldRegisterUser() throws Exception {
        // Arrange
        RegisterRequest request = new RegisterRequest("testuser", "password123", "John", "Doe");
        AuthenticationResponse response = new AuthenticationResponse("test_token");

        when(authenticationService.register(any(RegisterRequest.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test_token"));
    }

    @Test
    public void shouldAuthenticateUser() throws Exception {
        // Arrange
        AuthenticationRequest request = new AuthenticationRequest("testuser", "password123");
        AuthenticationResponse response = new AuthenticationResponse("test_token");

        when(authenticationService.authenticate(any(AuthenticationRequest.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test_token"));
    }

    @Test
    @WithMockUser(username = "testuser")
    public void shouldLogoutUser() throws Exception {
        try (MockedStatic<UserService> userServiceMockedStatic = Mockito.mockStatic(UserService.class)) {
            userServiceMockedStatic.when(UserService::getCurrentUserUsername).thenReturn(Optional.of("testuser"));

            // Act & Assert
            mockMvc.perform(post("/api/auth/logout"))
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void shouldNotLogoutUserWithoutSession() throws Exception {
        try (MockedStatic<UserService> userServiceMockedStatic = Mockito.mockStatic(UserService.class)) {
            userServiceMockedStatic.when(UserService::getCurrentUserUsername).thenReturn(Optional.empty());

            mockMvc.perform(post("/api/auth/logout"))
                    .andExpect(status().isUnauthorized());
        }
    }
}
