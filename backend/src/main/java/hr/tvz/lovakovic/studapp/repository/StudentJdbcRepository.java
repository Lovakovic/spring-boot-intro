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
        student.setFirstName(rs.getString("firstName"));
        student.setLastName(rs.getString("lastName"));
        student.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());
        student.setEctsPoints(rs.getInt("ectsPoints"));
        student.setEnrolledStudiesAtYear(rs.getInt("enrolledStudiesAtYear"));
        student.setCurrentSemester(rs.getInt("currentSemester"));
        student.setEmail(rs.getString("email"));
        student.setPhone(rs.getString("phone"));
        student.setMajor(rs.getString("major"));
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
        parameters.put("firstName", student.getFirstName());
        parameters.put("lastName", student.getLastName());
        parameters.put("dateOfBirth", student.getDateOfBirth());
        parameters.put("ectsPoints", student.getEctsPoints());
        parameters.put("enrolledStudiesAtYear", student.getEnrolledStudiesAtYear());
        parameters.put("currentSemester", student.getCurrentSemester());
        parameters.put("email", student.getEmail());
        parameters.put("phone", student.getPhone());
        parameters.put("major", student.getMajor());

        simpleJdbcInsert.execute(parameters);
        return student;
    }

    @Override
    public Student replace(String jmbag, Student newStudent) {
        String sql = "UPDATE student SET firstName = ?, lastName = ?, dateOfBirth = ?, ectsPoints = ?, " +
                "enrolledStudiesAtYear = ?, currentSemester = ?, email = ?, phone = ?, major = ? WHERE jmbag = ?";

        jdbcTemplate.update(sql,
                newStudent.getFirstName(),
                newStudent.getLastName(),
                newStudent.getDateOfBirth(),
                newStudent.getEctsPoints(),
                newStudent.getEnrolledStudiesAtYear(),
                newStudent.getCurrentSemester(),
                newStudent.getEmail(),
                newStudent.getPhone(),
                newStudent.getMajor(),
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
