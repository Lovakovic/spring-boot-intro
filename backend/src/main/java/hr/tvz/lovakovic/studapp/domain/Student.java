package hr.tvz.lovakovic.studapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String jmbag;
    private Integer ectsPoints;
}
