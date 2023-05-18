package hr.tvz.lovakovic.studapp.controller;

import hr.tvz.lovakovic.studapp.model.LoginRecord;
import hr.tvz.lovakovic.studapp.model.LoginRecordDTO;
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
    public List<LoginRecordDTO> getAllLogins() {
        return loginService.getAllLogins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoginRecordDTO> getLoginById(@PathVariable Long id) {
        return loginService.getLoginById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LoginRecordDTO addLogin(@RequestBody LoginRecord loginRecord) {
        return loginService.addLogin(loginRecord);
    }

    @PutMapping
    public LoginRecordDTO updateLogin(@RequestBody LoginRecord loginRecord) {
        return loginService.updateLogin(loginRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteLogin(@PathVariable Long id) {
        loginService.deleteLogin(id);
    }

    @GetMapping("/lastLogin/{userId}")
    public ResponseEntity<LoginRecordDTO> getLastLoginByUser(@PathVariable Long userId) {
        return loginService.getLastLoginByUser(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}