package hr.tvz.lovakovic.studapp.controller;

import hr.tvz.lovakovic.studapp.security.JWTFilter;
import hr.tvz.lovakovic.studapp.security.TokenProvider;
import hr.tvz.lovakovic.studapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    public LoginController(TokenProvider tokenProvider,
                           AuthenticationManagerBuilder authenticationManagerBuilder,
                           UserService userService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authenticate(@Valid @RequestBody LoginDTO login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getUsername(),
                login.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        userService.logUserLogin(login.getUsername()); // Delegate logging to the UserService

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String username = getUsernameFromRequest(request);
        if (username != null) {
            userService.logUserLogout(username);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private String getUsernameFromRequest(HttpServletRequest request) {
        String header = request.getHeader(JWTFilter.AUTHORIZATION_HEADER);
        if (header != null && header.startsWith("Bearer ")) {
            String authToken = header.substring(7);
            String username = tokenProvider.getUsernameFromToken(authToken);
            return username;
        } else {
            return null;
        }
    }



    /**
     * Return jwt token in body because Angular has problems with parsing plain string response entity
     */
    static class JWTToken {
        private String token;

        public JWTToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    static class LoginDTO {

        @NotNull
        private String username;

        @NotNull
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
