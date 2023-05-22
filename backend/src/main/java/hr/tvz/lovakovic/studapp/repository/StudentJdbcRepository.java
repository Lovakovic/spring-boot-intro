package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.model.AboutMe;
import hr.tvz.lovakovic.studapp.model.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Primary
public class StudentJdbcRepository implements StudentRepository {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public StudentJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("student");
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        List<Student> students = jdbcTemplate.query(sql, this::mapStudent);
        students.forEach(this::loadAboutMe);
        return students;
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String jmbag) {
        String sql = "SELECT * FROM student WHERE jmbag = ?";
        List<Student> students = jdbcTemplate.query(sql, new Object[]{jmbag}, this::mapStudent);
        students.forEach(this::loadAboutMe);
        return students.stream().findFirst();
    }

    @Override
    public Student save(Student student) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("jmbag", student.getJmbag());
        parameters.put("firstName", student.getFirstName());
        parameters.put("lastName", student.getLastName());
        parameters.put("dateOfBirth", student.getDateOfBirth());
        parameters.put("ectsPoints", student.getEctsPoints());

        simpleJdbcInsert.execute(parameters);
        return student;
    }

    @Override
    public Student replace(String jmbag, Student newStudent) {
        String sql = "UPDATE student SET firstName = ?, lastName = ?, dateOfBirth = ?, ectsPoints = ? WHERE jmbag = ?";

        jdbcTemplate.update(sql,
                newStudent.getFirstName(),
                newStudent.getLastName(),
                newStudent.getDateOfBirth(),
                newStudent.getEctsPoints(),
                jmbag);
        return newStudent;
    }

    @Override
    public Boolean deleteByJmbag(String jmbag) {
        // First we have to delete associated rows in student_course table
        String deleteAssociationsSql = "DELETE FROM student_course WHERE student_jmbag = ?";
        jdbcTemplate.update(deleteAssociationsSql, jmbag);

        String sql = "DELETE FROM student WHERE jmbag = ?";
        int affectedRows = jdbcTemplate.update(sql, jmbag);

        return affectedRows > 0;
    }

    private Student mapStudent(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setJmbag(rs.getString("jmbag"));
        student.setFirstName(rs.getString("firstName"));
        student.setLastName(rs.getString("lastName"));
        student.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());
        student.setEctsPoints(rs.getInt("ectsPoints"));
        return student;
    }

    private void loadAboutMe(Student student) {
        String aboutMeSql = "SELECT id, language, content FROM about_me WHERE jmbag = ?";
        Set<AboutMe> aboutMeSet = new HashSet<>(jdbcTemplate.query(aboutMeSql, new Object[] { student.getJmbag() },
                (rs, rowNum) -> {
                    AboutMe aboutMe = new AboutMe();
                    aboutMe.setId(rs.getInt("id"));
                    aboutMe.setStudent(student);
                    aboutMe.setLanguage(rs.getString("language"));
                    aboutMe.setContent(rs.getString("content"));
                    return aboutMe;
                }));
        student.setAboutMe(aboutMeSet);
    }
}
