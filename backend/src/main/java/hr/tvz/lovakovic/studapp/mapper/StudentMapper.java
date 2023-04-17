package hr.tvz.lovakovic.studapp.mapper;

import hr.tvz.lovakovic.studapp.command.StudentCommand;
import hr.tvz.lovakovic.studapp.model.Student;
import hr.tvz.lovakovic.studapp.model.StudentDTO;

import java.time.LocalDate;
import java.time.Period;

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
        String fullName = student.getFirstName() + " " + student.getLastName();
        boolean shouldPayTuition = Period.between(student.getDateOfBirth(), LocalDate.now()).getYears() >= 26;

        studentDTO.setJmbag(student.getJmbag());
        studentDTO.setEctsPoints(student.getEctsPoints());
        studentDTO.setShouldPayTuition(shouldPayTuition);
        studentDTO.setFullName(fullName);
        studentDTO.setEnrolledStudiesAtYear(student.getEnrolledStudiesAtYear());
        studentDTO.setCurrentSemester(student.getCurrentSemester());

        return studentDTO;
    }
}
