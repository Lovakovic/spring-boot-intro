package hr.tvz.lovakovic.studapp.controller;

import hr.tvz.lovakovic.studapp.security.JWTFilter;
import hr.tvz.lovakovic.studapp.security.TokenProvider;
import hr.tvz.lovakovic.studapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private UserService userService;

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Authentication authentication;

    private LoginController.LoginDTO loginDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        loginDTO = new LoginController.LoginDTO();
        loginDTO.setUsername("test");
        loginDTO.setPassword("password");
    }

    @Test
    public void testAuthenticate() {
        when(authenticationManagerBuilder.getObject()).thenReturn(authenticationManager);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(tokenProvider.createToken(authentication)).thenReturn("token");

        ResponseEntity<LoginController.JWTToken> response = loginController.authenticate(loginDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("token", response.getBody().getToken());
    }

    @Test
    public void testLogoutSuccessful() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader(JWTFilter.AUTHORIZATION_HEADER)).thenReturn("Bearer token");
        when(tokenProvider.getUsernameFromToken(any(String.class))).thenReturn("test");
        ResponseEntity<Void> response = loginController.logout(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLogoutFailure() {
        when(tokenProvider.getUsernameFromToken(any(String.class))).thenReturn(null);

        ResponseEntity<Void> response = loginController.logout(mock(HttpServletRequest.class));

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}