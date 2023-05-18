package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.mapper.UserMapper;
import hr.tvz.lovakovic.studapp.model.Login;
import hr.tvz.lovakovic.studapp.model.User;
import hr.tvz.lovakovic.studapp.model.UserDTO;
import hr.tvz.lovakovic.studapp.repository.LoginRepository;
import hr.tvz.lovakovic.studapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    @Override
    public Optional<UserDTO> findByUsernae(String username) {
        return userRepository.findOneByUsername(username).map(this::convertUserToDTO);
    }

    @Async
    @Override
    public void logUserLogin(String username) {
        Optional<User> userOptional = userRepository.findOneByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Login loginHistory = new Login();
            loginHistory.setUser(user);
            loginHistory.setRole(user.getAuthorities().iterator().next());
            loginHistory.setDateTimeLogin(LocalDateTime.now());
            loginRepository.save(loginHistory);
        }
    }

    @Async
    @Override
    public void logUserLogout(String username) {
        Optional<User> userOptional = userRepository.findOneByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Login lastLoginRecord = loginRepository.findTopByUserOrderByDateTimeLoginDesc(user);
            if (lastLoginRecord != null && lastLoginRecord.getDateTimeLogoff() == null) {
                lastLoginRecord.setDateTimeLogoff(LocalDateTime.now());
                loginRepository.save(lastLoginRecord);
            }
        }
    }


    @Override
    public UserDTO convertUserToDTO(User user) { return UserMapper.toDTO(user); }
}
