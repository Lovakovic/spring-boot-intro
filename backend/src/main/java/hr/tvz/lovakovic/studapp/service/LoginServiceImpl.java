package hr.tvz.lovakovic.studapp.service;


import hr.tvz.lovakovic.studapp.mapper.LoginMapper;
import hr.tvz.lovakovic.studapp.model.LoginRecord;
import hr.tvz.lovakovic.studapp.model.LoginRecordDTO;
import hr.tvz.lovakovic.studapp.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public List<LoginRecordDTO> getAllLogins() {
        return loginRepository.findAll().stream()
                .map(LoginMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<LoginRecordDTO> getLoginById(Long id) {
        return loginRepository.findById(id).map(LoginMapper::toDto);
    }

    public LoginRecordDTO addLogin(LoginRecord loginRecord) {
        return LoginMapper.toDto(loginRepository.save(loginRecord));
    }

    public LoginRecordDTO updateLogin(LoginRecord loginRecord) {
        return LoginMapper.toDto(loginRepository.save(loginRecord));
    }

    public void deleteLogin(Long id) {
        loginRepository.deleteById(id);
    }

    public Optional<LoginRecordDTO> getLastLoginByUser(Long userId) {
        return loginRepository.findTopByUserIdOrderByDateTimeLoginDesc(userId)
                .map(LoginMapper::toDto);
    }
}