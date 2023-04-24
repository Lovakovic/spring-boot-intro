package hr.tvz.lovakovic.studapp.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
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

    @NotNull(message = "Year of enrollment must not be empty")
    private Integer enrolledStudiesAtYear;

    @NotNull(message = "Current semester must not be empty")
    private Integer currentSemester;

    @NotBlank(message = "Email must not be empty")
    private String email;

    @NotBlank(message = "Phone number must not be empty")
    private String phone;

    @NotBlank(message = "Major must not be empty")
    private String major;
}