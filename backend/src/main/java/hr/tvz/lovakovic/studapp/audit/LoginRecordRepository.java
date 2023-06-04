package hr.tvz.lovakovic.studapp.audit;

import hr.tvz.lovakovic.studapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRecordRepository extends JpaRepository<LoginRecord, Long> {
    Optional<LoginRecord> findTopByUserIdOrderByDateTimeLoginDesc(Long id);
    LoginRecord findTopByUserOrderByDateTimeLoginDesc(User user);
}
