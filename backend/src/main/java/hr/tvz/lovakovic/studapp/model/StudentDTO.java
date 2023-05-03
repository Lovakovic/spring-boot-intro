package hr.tvz.lovakovic.studapp.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String jmbag;
    private Integer ectsPoints;
    private Integer enrolledStudiesAtYear;
    private Integer currentSemester;
}
