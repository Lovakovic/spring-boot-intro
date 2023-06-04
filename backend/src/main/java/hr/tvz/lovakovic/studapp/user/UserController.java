package hr.tvz.lovakovic.studapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return UserService.getCurrentUserUsername()
                .map(
                        usrename -> userService.findByUsername(usrename)
                                .map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build())
                ).orElseGet(() -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build());
    }
}
