package hr.tvz.lovakovic.studapp.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginDTO {

    private Long id;
    private String username;
    private String roleName;
    private LocalDateTime dateTimeLogin;
    private LocalDateTime dateTimeLogoff;
}