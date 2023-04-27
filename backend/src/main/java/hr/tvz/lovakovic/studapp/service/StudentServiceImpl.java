package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.exception.StudentAlreadyExistsException;
import hr.tvz.lovakovic.studapp.mapper.StudentMapper;
import hr.tvz.lovakovic.studapp.model.DetailStudentDTO;
import hr.tvz.lovakovic.studapp.model.Student;
import hr.tvz.lovakovic.studapp.model.StudentCommand;
import hr.tvz.lovakovic.studapp.model.StudentDTO;
import hr.tvz.lovakovic.studapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<DetailStudentDTO> findAllDetail() {
        return studentRepository.findAll().stream()
                .map(this::convertToDetailStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO findStudentByJMBAG(String JMBAG) {
        return studentRepository.findStudentByJMBAG(JMBAG)
                .map(this::convertToStudentDTO)
                .orElse(null);
    }

    @Override
    public DetailStudentDTO findDetailStudentByJMBAG(String JMBAG) {
        return studentRepository.findStudentByJMBAG(JMBAG)
                .map(this::convertToDetailStudentDTO)
                .orElse(null);
    }

    @Override
    public DetailStudentDTO addStudent(StudentCommand studentCommand) {
        // Check if the student already exists
        if (studentRepository.findStudentByJMBAG(studentCommand.getJmbag()).isPresent()) {
            throw new StudentAlreadyExistsException("A student with the given JMBAG already exists.");
        }

        Student savedStudent = studentRepository.save(StudentMapper.fromCommand(studentCommand));
        return convertToDetailStudentDTO(savedStudent);
    }

    @Override
    public Pair<Boolean, DetailStudentDTO> putStudent(String jmbag, StudentCommand studentCommand) {
        Optional<Student> existingStudent = studentRepository.findStudentByJMBAG(jmbag);

        if (existingStudent.isPresent()) {
            // Update existing student
            Student updatedStudent = StudentMapper.fromCommand(studentCommand);
            updatedStudent.setJmbag(jmbag);
            return Pair.of(false, convertToDetailStudentDTO(studentRepository.replace(jmbag, updatedStudent)));
        } else {
            // Create new student
            Student newStudent = StudentMapper.fromCommand(studentCommand);
            Student savedStudent = studentRepository.save(newStudent);
            return Pair.of(true, convertToDetailStudentDTO(savedStudent));
        }
    }

    @Override
    public Boolean deleteStudentByJMBAG(String jmbag) {
        return studentRepository.deleteByJmbag(jmbag);
    }

    @Override
    public StudentDTO convertToStudentDTO(Student student) {
        return StudentMapper.toDTO(student);
    }

    @Override
    public DetailStudentDTO convertToDetailStudentDTO(Student student) {
        return StudentMapper.toDetailDTO(student);
    }
}