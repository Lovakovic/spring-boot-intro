package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
