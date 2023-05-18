package hr.tvz.lovakovic.studapp.mapper;

import hr.tvz.lovakovic.studapp.model.Login;
import hr.tvz.lovakovic.studapp.model.LoginDTO;

public class LoginMapper {
    public static LoginDTO toDto(Login login) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setId(login.getId());
        loginDTO.setUsername(login.getUser().getUsername());
        loginDTO.setRoleName(login.getRole().getName());
        loginDTO.setDateTimeLogin(login.getDateTimeLogin());
        loginDTO.setDateTimeLogoff(login.getDateTimeLogoff());
        return loginDTO;
    }
}
