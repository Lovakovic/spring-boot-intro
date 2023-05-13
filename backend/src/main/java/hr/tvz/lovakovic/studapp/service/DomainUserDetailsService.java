package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        getAuthorities(user)))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(hr.tvz.lovakovic.studapp.model.User user) {
        return user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }
}
