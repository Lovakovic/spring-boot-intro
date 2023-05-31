package hr.tvz.lovakovic.studapp.service;


import hr.tvz.lovakovic.studapp.mapper.LoginMapper;
import hr.tvz.lovakovic.studapp.model.LoginRecordDTO;
import hr.tvz.lovakovic.studapp.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginRecordServiceImpl implements LoginRecordService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginRecordServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public List<LoginRecordDTO> getAllLogins() {
        return loginRepository.findAll().stream()
                .map(LoginMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LoginRecordDTO> getLoginById(Long id) {
        return loginRepository.findById(id).map(LoginMapper::toDto);
    }

    @Override
    public Optional<LoginRecordDTO> getLastLoginByUser(Long userId) {
        return loginRepository.findTopByUserIdOrderByDateTimeLoginDesc(userId)
                .map(LoginMapper::toDto);
    }
}