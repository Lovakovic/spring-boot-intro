package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.exception.StudentAlreadyExistsException;
import hr.tvz.lovakovic.studapp.mapper.StudentMapper;
import hr.tvz.lovakovic.studapp.model.Student;
import hr.tvz.lovakovic.studapp.model.StudentCommand;
import hr.tvz.lovakovic.studapp.model.StudentDTO;
import hr.tvz.lovakovic.studapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static hr.tvz.lovakovic.studapp.repository.StudentRepositoryDev.students;

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
    public StudentDTO addStudent(StudentCommand studentCommand) {
        // Check if the student already exists
        if (studentRepository.findStudentByJMBAG(studentCommand.getJmbag()).isPresent()) {
            throw new StudentAlreadyExistsException("A student with the given JMBAG already exists.");
        }

        Student savedStudent = studentRepository.save(StudentMapper.fromCommand(studentCommand));
        return StudentMapper.toDTO(savedStudent);
    }

    @Override
    public Optional<StudentDTO> putStudent(String jmbag, StudentCommand studentCommand) {
        Optional<Student> existingStudent = studentRepository.findStudentByJMBAG(jmbag);


        if (existingStudent.isPresent()) {
            // Update existing student
            Student updatedStudent = StudentMapper.fromCommand(studentCommand);
            updatedStudent.setJmbag(jmbag);
            studentRepository.replace(jmbag, updatedStudent);
            return Optional.of(StudentMapper.toDTO(updatedStudent));
        } else {
            // Create new student
            Student newStudent = StudentMapper.fromCommand(studentCommand);
            Student savedStudent = studentRepository.save(newStudent);
            return Optional.of(StudentMapper.toDTO(savedStudent));
        }
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
        return StudentMapper.toDTO(student);
    }
}