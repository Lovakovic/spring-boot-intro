package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.model.Login;
import hr.tvz.lovakovic.studapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findTopByUserIdOrderByDateTimeLoginDesc(Long id);
    Login findTopByUserOrderByDateTimeLoginDesc(User user);
}
