package hr.tvz.lovakovic.studapp.student;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCommand {
    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @NotNull(message = "Date of birth must not be null")
    @Past(message = "Date of birth cannot be in future or present")
    private LocalDate dateOfBirth;

    @NotBlank(message = "JMBAG must not be blank")
    @Size(min = 10, max = 10, message = "JMBAG must be exactly 10 characters long")
    private String jmbag;

    @NotNull(message = "ECTS points must not be null")
    private Integer ectsPoints;
}