package hr.tvz.lovakovic.studapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
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
}
