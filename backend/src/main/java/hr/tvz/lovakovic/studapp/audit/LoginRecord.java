package hr.tvz.lovakovic.studapp.audit;

import hr.tvz.lovakovic.studapp.user.Role;
import hr.tvz.lovakovic.studapp.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "login_history")
public class LoginRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "date_time_login", nullable = false)
    private LocalDateTime dateTimeLogin;

    @Column(name = "date_time_logoff")
    private LocalDateTime dateTimeLogoff;
}