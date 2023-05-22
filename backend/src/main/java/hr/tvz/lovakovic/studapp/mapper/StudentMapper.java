package hr.tvz.lovakovic.studapp.mapper;

import hr.tvz.lovakovic.studapp.model.AboutMe;
import hr.tvz.lovakovic.studapp.model.Student;
import hr.tvz.lovakovic.studapp.model.StudentCommand;
import hr.tvz.lovakovic.studapp.model.StudentDTO;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

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

        Map<String, String> aboutMeTexts = new HashMap<>();
        for(AboutMe aboutMe : student.getAboutMe()) {
            aboutMeTexts.put(
                    aboutMe.getLanguage(),
                    aboutMe.getContent()
            );
        }
        studentDTO.setAboutMe(aboutMeTexts);

        return studentDTO;
    }
}
