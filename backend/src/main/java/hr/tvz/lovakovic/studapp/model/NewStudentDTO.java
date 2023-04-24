package hr.tvz.lovakovic.studapp.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewStudentDTO {
    private String jmbag;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Integer ectsPoints;
    private Integer enrolledStudiesAtYear;
    private Integer currentSemester;
    private String email;
    private String phone;
    private String major;
}
