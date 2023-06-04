package hr.tvz.lovakovic.studapp.audit;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginRecordDTO {

    private Long id;
    private String username;
    private String roleName;
    private LocalDateTime dateTimeLogin;
    private LocalDateTime dateTimeLogoff;
}
