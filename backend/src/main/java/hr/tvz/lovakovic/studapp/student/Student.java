package hr.tvz.lovakovic.studapp.student;

import hr.tvz.lovakovic.studapp.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {
    @Id
    private String jmbag;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Integer ectsPoints;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_jmbag"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;
}
