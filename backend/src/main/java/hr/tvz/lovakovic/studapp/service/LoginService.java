package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.model.Login;
import hr.tvz.lovakovic.studapp.model.LoginDTO;

import java.util.List;
import java.util.Optional;

public interface LoginService {
    List<LoginDTO> getAllLogins();
    Optional<LoginDTO> getLoginById(Long id);
    LoginDTO addLogin(Login login);
    LoginDTO updateLogin(Login login);
    void deleteLogin(Long id);
    Optional<LoginDTO> getLastLoginByUser(Long userId);
}
