package hr.tvz.lovakovic.studapp.user;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String role;
}
