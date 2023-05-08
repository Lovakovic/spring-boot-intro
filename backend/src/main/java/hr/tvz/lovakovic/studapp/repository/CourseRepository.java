package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAll();
    List<Course> findByStudents_Jmbag(String jmbag);

    // First
    Course findFirstByName(String name);

    // Top
    List<Course> findTop3ByOrderByEctsDesc();

    // Distinct
    List<Course> findDistinctByName(String name);

    // Equals
    List<Course> findByNameEquals(String name);

    // Is
    List<Course> findByNameIs(String name);

    // Not
    List<Course> findByNameNot(String name);

    // In
    List<Course> findByIdIn(List<Long> ids);

    // Not in
    List<Course> findByIdNotIn(List<Long> ids);

    // Before
    List<Course> findByStudents_DateOfBirthBefore(LocalDate date);

    // After
    List<Course> findByStudents_DateOfBirthAfter(LocalDate date);

    // Between
    List<Course> findByStudents_DateOfBirthBetween(LocalDate startDate, LocalDate endDate);

    // @Query
    @Query("SELECT c FROM Course c WHERE c.ects = :ects")
    List<Course> findByEctsValue(@Param("ects") Integer ects);
}
