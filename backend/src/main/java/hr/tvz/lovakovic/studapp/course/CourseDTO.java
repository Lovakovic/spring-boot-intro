package hr.tvz.lovakovic.studapp.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Integer Id;
    private String name;
    private Integer ects;
}
