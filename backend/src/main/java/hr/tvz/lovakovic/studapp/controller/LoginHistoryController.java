package hr.tvz.lovakovic.studapp.controller;

import hr.tvz.lovakovic.studapp.model.Login;
import hr.tvz.lovakovic.studapp.model.LoginDTO;
import hr.tvz.lovakovic.studapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginHistoryController {

    private final LoginService loginService;

    @Autowired
    public LoginHistoryController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public List<LoginDTO> getAllLogins() {
        return loginService.getAllLogins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoginDTO> getLoginById(@PathVariable Long id) {
        return loginService.getLoginById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LoginDTO addLogin(@RequestBody Login login) {
        return loginService.addLogin(login);
    }

    @PutMapping
    public LoginDTO updateLogin(@RequestBody Login login) {
        return loginService.updateLogin(login);
    }

    @DeleteMapping("/{id}")
    public void deleteLogin(@PathVariable Long id) {
        loginService.deleteLogin(id);
    }

    @GetMapping("/lastLogin/{userId}")
    public ResponseEntity<LoginDTO> getLastLoginByUser(@PathVariable Long userId) {
        return loginService.getLastLoginByUser(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}