package hr.tvz.lovakovic.studapp.model;

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
    private Integer enrolledStudiesAtYear;
    private Integer currentSemester;
    private String email;
    private String phone;
    private String major;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_jmbag"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Course> courses;
}
