package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.model.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@Profile("dev")
public class StudentRepositoryDev implements StudentRepository {
    public static List<Student> students = new ArrayList<>();

//    static {
//        students.add(new Student("9283758271", "Ivan", "Markovic", LocalDate.of(2000, 1, 1),
//                60, 2019, 4, "ivan.markovic@student.com", "+38591234567", "Computer Science"));
//        students.add(new Student("0284729172","Horvat", "Horvatinic", LocalDate.of(2001, 2, 2),
//                78, 2021, 3, "horvat.horvatinic@student.com", "+38598765432", "Business Administration"));
//        students.add(new Student("7261948261","Ana", "Babic", LocalDate.of(2002, 3, 5),
//                72, 2020, 2, "ana.babic@student.com", "+38595544333", "Electrical Engineering"));
//        students.add(new Student("8826183962","Petar", "Novak", LocalDate.of(2000, 5, 10),
//                85, 2019, 4, "petar.novak@student.com", "+38591666666", "Mechanical Engineering"));
//        students.add(new Student("5719273628","Ivana", "Kovac", LocalDate.of(1999, 9, 20),
//                90, 2018, 2, "ivana.kovac@student.com", "+38597777777", "Mathematics"));
//        students.add(new Student("2957281941","Luka", "Hodak", LocalDate.of(2003, 4, 15),
//                65, 2022, 1, "luka.hodak@student.com", "+38595555555", "Environmental Science"));
//        students.add(new Student("4791826392","Jana", "Bogdan", LocalDate.of(2001, 7, 25),
//                80, 2021, 2, "jana.bogdan@student.com", "+38594444444", "Architecture"));
//    }


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

    @Override
    public Student replace(String jmbag, Student newStudent) {
        int index = -1;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getJmbag().equals(jmbag)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            students.set(index, newStudent);
            return newStudent;
        } else {
            throw new NoSuchElementException("No student found with JMBAG: " + jmbag);
        }
    }

    @Override
    public Boolean deleteByJmbag(String jmbag) {
        return students.removeIf(student -> student.getJmbag().equals(jmbag));
    }
}