package hr.tvz.lovakovic.studapp.mapper;

import hr.tvz.lovakovic.studapp.model.StudentCommand;
import hr.tvz.lovakovic.studapp.model.Student;
import hr.tvz.lovakovic.studapp.model.StudentDTO;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class StudentMapper {
    public static Student fromCommand(StudentCommand studentCommand) {
        return new Student(
                studentCommand.getFirstName(),
                studentCommand.getLastName(),
                studentCommand.getDateOfBirth(),
                studentCommand.getJmbag(),
                studentCommand.getEctsPoints(),
                studentCommand.getEnrolledStudiesAtYear(),
                studentCommand.getCurrentSemester()
        );
    }

    public static StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setJmbag(student.getJmbag());
        studentDTO.setEctsPoints(student.getEctsPoints());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setDateOfBirth(student.getDateOfBirth().format(DateTimeFormatter.ISO_DATE));
        studentDTO.setEnrolledStudiesAtYear(student.getEnrolledStudiesAtYear());
        studentDTO.setCurrentSemester(student.getCurrentSemester());

        return studentDTO;
    }
}
