package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.domain.Student;
import hr.tvz.lovakovic.studapp.command.StudentCommand;
import hr.tvz.lovakovic.studapp.dto.StudentDTO;
import hr.tvz.lovakovic.studapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static hr.tvz.lovakovic.studapp.repository.StudentRepositoryImpl.students;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(this::convertToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO findStudentByJMBAG(String JMBAG) {
        return studentRepository.findStudentByJMBAG(JMBAG)
                .map(this::convertToStudentDTO)
                .orElse(null);
    }

    @Override
    public Boolean addStudent(StudentCommand studentCommand) {
        // Check if the student already exists
        if (studentRepository.findStudentByJMBAG(studentCommand.getJmbag()).isPresent()) {
            return false;
        }

        Student student = new Student(studentCommand.getFirstName(), studentCommand.getLastName(),
                studentCommand.getDateOfBirth(), studentCommand.getJmbag(), studentCommand.getEctsPoints());
        studentRepository.save(student);
        return true;
    }


    @Override
    public Boolean deleteStudentByJMBAG(String jmbag) {
        Optional<Student> studentToDelete = studentRepository.findStudentByJMBAG(jmbag);
        if (studentToDelete.isPresent()) {
            students.remove(studentToDelete.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public StudentDTO convertToStudentDTO(Student student) {
        boolean shouldPayTuition = Period.between(student.getDateOfBirth(), LocalDate.now()).getYears() >= 26;
        return new StudentDTO(student.getJmbag(), student.getEctsPoints(), shouldPayTuition);
    }
}