package hr.tvz.lovakovic.studapp.audit;

import java.util.List;
import java.util.Optional;

public interface LoginRecordService {
    List<LoginRecordDTO> getAllLogins();
    Optional<LoginRecordDTO> getLoginById(Long id);
    Optional<LoginRecordDTO> getLastLoginByUser(Long userId);
}
