package hr.tvz.lovakovic.studapp.mapper;

import hr.tvz.lovakovic.studapp.model.DetailStudentDTO;
import hr.tvz.lovakovic.studapp.model.Student;
import hr.tvz.lovakovic.studapp.model.StudentCommand;
import hr.tvz.lovakovic.studapp.model.StudentDTO;

import java.time.format.DateTimeFormatter;

public class StudentMapper {
    public static Student fromCommand(StudentCommand studentCommand) {
        return Student.builder()
                .jmbag(studentCommand.getJmbag())
                .firstName(studentCommand.getFirstName())
                .lastName(studentCommand.getLastName())
                .dateOfBirth(studentCommand.getDateOfBirth())
                .ectsPoints(studentCommand.getEctsPoints())
                .enrolledStudiesAtYear(studentCommand.getEnrolledStudiesAtYear())
                .currentSemester(studentCommand.getCurrentSemester())
                .email(studentCommand.getEmail())
                .phone(studentCommand.getPhone())
                .major(studentCommand.getMajor())
                .build();
    }

    public static StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setJmbag(student.getJmbag());
        studentDTO.setEctsPoints(student.getEctsPoints());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setTuitionShouldBePaid(student.getEctsPoints() < student.getCurrentSemester() * 25);
        studentDTO.setDateOfBirth(student.getDateOfBirth().format(DateTimeFormatter.ISO_DATE));
        studentDTO.setEnrolledStudiesAtYear(student.getEnrolledStudiesAtYear());
        studentDTO.setCurrentSemester(student.getCurrentSemester());

        return studentDTO;
    }

    public static DetailStudentDTO toDetailDTO(Student student) {
        DetailStudentDTO detailStudentDTO = new DetailStudentDTO();

        detailStudentDTO.setJmbag(student.getJmbag());
        detailStudentDTO.setEctsPoints(student.getEctsPoints());
        detailStudentDTO.setFirstName(student.getFirstName());
        detailStudentDTO.setLastName(student.getLastName());
        detailStudentDTO.setDateOfBirth(student.getDateOfBirth().format(DateTimeFormatter.ISO_DATE));
        detailStudentDTO.setEnrolledStudiesAtYear(student.getEnrolledStudiesAtYear());
        detailStudentDTO.setCurrentSemester(student.getCurrentSemester());
        detailStudentDTO.setEmail(student.getEmail());
        detailStudentDTO.setPhone(student.getPhone());
        detailStudentDTO.setMajor(student.getMajor());

        return detailStudentDTO;
    }
}
