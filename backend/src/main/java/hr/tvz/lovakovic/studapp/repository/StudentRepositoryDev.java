package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.model.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("dev")
public class StudentRepositoryDev implements StudentRepository {
    public static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student("Ivan", "Markovic", LocalDate.of(2000, 1, 1),
                "9283758271", 60, 2019, 4));
        students.add(new Student("Horvat", "Horvatinic", LocalDate.of(2001, 2, 2),
                "0284729172", 78, 2021, 3));
        students.add(new Student("Ana", "Babic", LocalDate.of(2002, 3, 5),
                "7261948261", 72, 2020, 2));
        students.add(new Student("Petar", "Novak", LocalDate.of(2000, 5, 10),
                "8826183962", 85, 2019, 4));
        students.add(new Student("Ivana", "Kovac", LocalDate.of(1999, 9, 20),
                "5719273628", 90, 2018, 2));
        students.add(new Student("Luka", "Hodak", LocalDate.of(2003, 4, 15),
                "2957281941", 65, 2022, 1));
        students.add(new Student("Jana", "Bogdan", LocalDate.of(2001, 7, 25),
                "4791826392", 80, 2021, 2));
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