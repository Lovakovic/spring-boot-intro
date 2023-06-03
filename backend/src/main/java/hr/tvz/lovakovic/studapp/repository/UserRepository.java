package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findOneByUsername(String username);
}
