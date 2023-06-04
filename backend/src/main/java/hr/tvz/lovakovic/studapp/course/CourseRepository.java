package hr.tvz.lovakovic.studapp.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAll();
    List<Course> findByStudents_Jmbag(String jmbag);
}
