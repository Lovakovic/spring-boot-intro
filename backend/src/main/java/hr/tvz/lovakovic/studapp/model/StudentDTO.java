package hr.tvz.lovakovic.studapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private String jmbag;
    private Integer ectsPoints;
    private Boolean tuitionShouldBePaid;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Integer enrolledStudiesAtYear;
    private Integer currentSemester;
}
