package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.model.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Profile("prod")
public class StudentRepositoryProd implements StudentRepository{

    public static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student("0246099663","Ivo", "Mirkovic", LocalDate.of(2000, 9, 22),
                 176, 2019, 6));
        students.add(new Student("0182758279","Ivana", "Ivanovic", LocalDate.of(1998, 1, 1),
                 150, 2019, 6));
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String JMBAG) {
        return students.stream()
                .filter(student -> student.getJmbag().equals(JMBAG))
                .findFirst();
    }

    @Override
    public Student save(Student student) {
        students.add(student);
        return student;
    }
}
