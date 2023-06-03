package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.model.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Primary
public class StudentJdbcRepository implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public StudentJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("student");
    }

    private final RowMapper<Student> studentRowMapper = (rs, rowNum) -> {
        Student student = new Student();
        student.setJmbag(rs.getString("jmbag"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        student.setEctsPoints(rs.getInt("ects_points"));
        return student;
    };

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, studentRowMapper);
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String jmbag) {
        String sql = "SELECT * FROM student WHERE jmbag = ?";
        return jdbcTemplate.query(sql, studentRowMapper, jmbag).stream().findFirst();
    }

    @Override
    public Student save(Student student) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("jmbag", student.getJmbag());
        parameters.put("first_name", student.getFirstName());
        parameters.put("last_name", student.getLastName());
        parameters.put("date_of_birth", student.getDateOfBirth());
        parameters.put("ects_points", student.getEctsPoints());

        simpleJdbcInsert.execute(parameters);
        return student;
    }

    @Override
    public Student replace(String jmbag, Student newStudent) {
        String sql = "UPDATE student SET first_name = ?, last_name = ?, date_of_birth = ?, ects_points = ? WHERE jmbag = ?";

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
}