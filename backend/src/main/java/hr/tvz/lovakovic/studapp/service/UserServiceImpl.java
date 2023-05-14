package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.mapper.UserMapper;
import hr.tvz.lovakovic.studapp.model.User;
import hr.tvz.lovakovic.studapp.model.UserDTO;
import hr.tvz.lovakovic.studapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserDTO> findByUsernae(String username) {
        return userRepository.findOneByUsername(username).map(this::convertUserToDTO);
    }

    @Override
    public UserDTO convertUserToDTO(User user) { return UserMapper.toDTO(user); }
}
