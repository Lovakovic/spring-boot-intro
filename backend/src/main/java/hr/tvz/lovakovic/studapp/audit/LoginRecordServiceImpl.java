package hr.tvz.lovakovic.studapp.audit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginRecordServiceImpl implements LoginRecordService {

    private final LoginRecordRepository loginRecordRepository;

    @Autowired
    public LoginRecordServiceImpl(LoginRecordRepository loginRecordRepository) {
        this.loginRecordRepository = loginRecordRepository;
    }

    @Override
    public List<LoginRecordDTO> getAllLogins() {
        return loginRecordRepository.findAll().stream()
                .map(LoginRecordMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LoginRecordDTO> getLoginById(Long id) {
        return loginRecordRepository.findById(id).map(LoginRecordMapper::toDto);
    }

    @Override
    public Optional<LoginRecordDTO> getLastLoginByUser(Long userId) {
        return loginRecordRepository.findTopByUserIdOrderByDateTimeLoginDesc(userId)
                .map(LoginRecordMapper::toDto);
    }
}