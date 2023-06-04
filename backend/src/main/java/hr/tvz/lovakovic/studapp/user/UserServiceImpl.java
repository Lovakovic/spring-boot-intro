package hr.tvz.lovakovic.studapp.user;

import hr.tvz.lovakovic.studapp.audit.LoginRecord;
import hr.tvz.lovakovic.studapp.audit.LoginRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LoginRecordRepository loginRecordRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, LoginRecordRepository loginRecordRepository) {
        this.userRepository = userRepository;
        this.loginRecordRepository = loginRecordRepository;
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
            loginRecordHistory.setRole(user.getRole());
            loginRecordHistory.setDateTimeLogin(LocalDateTime.now());
            loginRecordRepository.save(loginRecordHistory);
        }
    }

    @Async
    @Override
    public void logUserLogout(String username) {
        Optional<User> userOptional = userRepository.findOneByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            LoginRecord lastLoginRecordRecord = loginRecordRepository.findTopByUserOrderByDateTimeLoginDesc(user);
            if (lastLoginRecordRecord != null && lastLoginRecordRecord.getDateTimeLogoff() == null) {
                lastLoginRecordRecord.setDateTimeLogoff(LocalDateTime.now());
                loginRecordRepository.save(lastLoginRecordRecord);
            }
        }
    }


    @Override
    public UserDTO convertUserToDTO(User user) { return UserMapper.toDTO(user); }
}
