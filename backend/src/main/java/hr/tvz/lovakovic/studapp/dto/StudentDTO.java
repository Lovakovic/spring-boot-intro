package hr.tvz.lovakovic.studapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private String jmbag;
    private Integer ectsPoints;
    private Boolean shouldPayTuition;

    // New attributes
    private String fullName;
    private Integer enrolledAtYear;
    private Integer currentSemester;
}
