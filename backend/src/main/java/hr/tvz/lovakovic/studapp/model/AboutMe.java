package hr.tvz.lovakovic.studapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "about_me")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AboutMe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "jmbag", nullable = false)
    private Student student;

    @Column(name = "language")
    private String language;

    @Column(name = "content")
    private String content;
}
