package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.model.LoginRecord;
import hr.tvz.lovakovic.studapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<LoginRecord, Long> {
    Optional<LoginRecord> findTopByUserIdOrderByDateTimeLoginDesc(Long id);
    LoginRecord findTopByUserOrderByDateTimeLoginDesc(User user);
}
