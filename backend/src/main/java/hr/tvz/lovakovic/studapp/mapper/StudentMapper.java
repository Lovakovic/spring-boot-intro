package hr.tvz.lovakovic.studapp.mapper;

import hr.tvz.lovakovic.studapp.model.NewStudentDTO;
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

    public static NewStudentDTO toNewDTO(Student student) {
        NewStudentDTO newStudentDTO = new NewStudentDTO();

        newStudentDTO.setJmbag(student.getJmbag());
        newStudentDTO.setEctsPoints(student.getEctsPoints());
        newStudentDTO.setFirstName(student.getFirstName());
        newStudentDTO.setLastName(student.getLastName());
        newStudentDTO.setDateOfBirth(student.getDateOfBirth().format(DateTimeFormatter.ISO_DATE));
        newStudentDTO.setEnrolledStudiesAtYear(student.getEnrolledStudiesAtYear());
        newStudentDTO.setCurrentSemester(student.getCurrentSemester());
        newStudentDTO.setEmail(student.getEmail());
        newStudentDTO.setPhone(student.getPhone());
        newStudentDTO.setMajor(student.getMajor());

        return newStudentDTO;
    }
}
