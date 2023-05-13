package hr.tvz.lovakovic.studapp.service;

import org.springframework.security.core.Authentication;

public interface TokenProviderService {
    String createToken(Authentication authentication);
    Authentication getAuthentication(String token);
    Boolean validateToken(String token);
}
