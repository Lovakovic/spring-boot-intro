package hr.tvz.lovakovic.studapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "login_history")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Authority role;

    @Column(name = "date_time_login", nullable = false)
    private LocalDateTime dateTimeLogin;

    @Column(name = "date_time_logoff")
    private LocalDateTime dateTimeLogoff;
}

