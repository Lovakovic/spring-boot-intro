package hr.tvz.lovakovic.studapp.mapper;

import hr.tvz.lovakovic.studapp.model.DetailedStudentDTO;
import hr.tvz.lovakovic.studapp.model.Student;
import hr.tvz.lovakovic.studapp.model.StudentCommand;
import hr.tvz.lovakovic.studapp.model.StudentDTO;

import java.time.format.DateTimeFormatter;

public class StudentMapper {
    public static Student fromCommand(StudentCommand studentCommand) {
        return new Student(
                studentCommand.getJmbag(),
                studentCommand.getFirstName(),
                studentCommand.getLastName(),
                studentCommand.getDateOfBirth(),
                studentCommand.getEctsPoints(),
                studentCommand.getEnrolledStudiesAtYear(),
                studentCommand.getCurrentSemester(),
                studentCommand.getEmail(),
                studentCommand.getPhone(),
                studentCommand.getMajor()
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

    public static DetailedStudentDTO toDetailDTO(Student student) {
        DetailedStudentDTO detailedStudentDTO = new DetailedStudentDTO();

        detailedStudentDTO.setJmbag(student.getJmbag());
        detailedStudentDTO.setEctsPoints(student.getEctsPoints());
        detailedStudentDTO.setFirstName(student.getFirstName());
        detailedStudentDTO.setLastName(student.getLastName());
        detailedStudentDTO.setDateOfBirth(student.getDateOfBirth().format(DateTimeFormatter.ISO_DATE));
        detailedStudentDTO.setEnrolledStudiesAtYear(student.getEnrolledStudiesAtYear());
        detailedStudentDTO.setCurrentSemester(student.getCurrentSemester());
        detailedStudentDTO.setEmail(student.getEmail());
        detailedStudentDTO.setPhone(student.getPhone());
        detailedStudentDTO.setMajor(student.getMajor());

        return detailedStudentDTO;
    }
}
