package hr.tvz.lovakovic.studapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private String jmbag;
    private Integer ectsPoints;
    private Boolean shouldPayTuition;

    // New attributes
    private String fullName;
    private Integer enrolledStudiesAtYear;
    private Integer currentSemester;
}
