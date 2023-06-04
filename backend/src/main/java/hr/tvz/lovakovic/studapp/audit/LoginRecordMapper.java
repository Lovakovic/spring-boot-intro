package hr.tvz.lovakovic.studapp.audit;

public class LoginRecordMapper {
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
