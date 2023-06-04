package hr.tvz.lovakovic.studapp.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginRecordController {

    private final LoginRecordService loginRecordService;

    @Autowired
    public LoginRecordController(LoginRecordService loginRecordService) {
        this.loginRecordService = loginRecordService;
    }

    @GetMapping
    public List<LoginRecordDTO> getAllLogins() {
        return loginRecordService.getAllLogins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoginRecordDTO> getLoginById(@PathVariable Long id) {
        return loginRecordService.getLoginById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/lastLogin/{userId}")
    public ResponseEntity<LoginRecordDTO> getLastLoginByUser(@PathVariable Long userId) {
        return loginRecordService.getLastLoginByUser(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}