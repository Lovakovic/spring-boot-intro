package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.mapper.UserMapper;
import hr.tvz.lovakovic.studapp.model.LoginRecord;
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
    public Optional<UserDTO> findByUsername(String username) {
        return userRepository.findOneByUsername(username).map(this::convertUserToDTO);
    }

    @Async
    @Override
    public void logUserLogin(String username) {
        Optional<User> userOptional = userRepository.findOneByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            LoginRecord loginRecordHistory = new LoginRecord();
            loginRecordHistory.setUser(user);
            loginRecordHistory.setRole(user.getAuthorities().iterator().next());
            loginRecordHistory.setDateTimeLogin(LocalDateTime.now());
            loginRepository.save(loginRecordHistory);
        }
    }

    @Async
    @Override
    public void logUserLogout(String username) {
        Optional<User> userOptional = userRepository.findOneByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            LoginRecord lastLoginRecordRecord = loginRepository.findTopByUserOrderByDateTimeLoginDesc(user);
            if (lastLoginRecordRecord != null && lastLoginRecordRecord.getDateTimeLogoff() == null) {
                lastLoginRecordRecord.setDateTimeLogoff(LocalDateTime.now());
                loginRepository.save(lastLoginRecordRecord);
            }
        }
    }


    @Override
    public UserDTO convertUserToDTO(User user) { return UserMapper.toDTO(user); }
}
