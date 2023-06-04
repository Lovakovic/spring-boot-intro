package hr.tvz.lovakovic.studapp.student;

import java.time.format.DateTimeFormatter;

public class StudentMapper {
    public static Student fromCommand(StudentCommand studentCommand) {
        return Student.builder()
                .jmbag(studentCommand.getJmbag())
                .firstName(studentCommand.getFirstName())
                .lastName(studentCommand.getLastName())
                .dateOfBirth(studentCommand.getDateOfBirth())
                .ectsPoints(studentCommand.getEctsPoints())
                .build();
    }

    public static StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setJmbag(student.getJmbag());
        studentDTO.setEctsPoints(student.getEctsPoints());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setTuitionShouldBePaid(student.getEctsPoints() < 30);
        studentDTO.setDateOfBirth(student.getDateOfBirth().format(DateTimeFormatter.ISO_DATE));

        return studentDTO;
    }
}
