package hr.tvz.lovakovic.studapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String jmbag;
    private Integer ectsPoints;
    private Integer enrolledStudiesAtYear;
    private Integer currentSemester;
}
