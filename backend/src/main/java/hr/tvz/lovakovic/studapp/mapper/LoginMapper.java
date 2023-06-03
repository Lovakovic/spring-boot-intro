package hr.tvz.lovakovic.studapp.mapper;

import hr.tvz.lovakovic.studapp.model.LoginRecord;
import hr.tvz.lovakovic.studapp.model.LoginRecordDTO;

public class LoginMapper {
    public static LoginRecordDTO toDto(LoginRecord loginRecord) {
        LoginRecordDTO loginRecordDTO = new LoginRecordDTO();
        loginRecordDTO.setId(loginRecord.getId());
        loginRecordDTO.setUsername(loginRecord.getUser().getUsername());
        loginRecordDTO.setRoleName(String.valueOf(loginRecord.getRole()));
        loginRecordDTO.setDateTimeLogin(loginRecord.getDateTimeLogin());
        loginRecordDTO.setDateTimeLogoff(loginRecord.getDateTimeLogoff());
        return loginRecordDTO;
    }
}
