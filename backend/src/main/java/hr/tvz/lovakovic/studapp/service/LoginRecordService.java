package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.model.LoginRecordDTO;

import java.util.List;
import java.util.Optional;

public interface LoginRecordService {
    List<LoginRecordDTO> getAllLogins();
    Optional<LoginRecordDTO> getLoginById(Long id);
    Optional<LoginRecordDTO> getLastLoginByUser(Long userId);
}
