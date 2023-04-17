package hr.tvz.lovakovic.studapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fakultet {
    private String name;
    private String street;
    private String city;
    private Integer debtAmount;
    private LocalDate nextPromotionDate;
}
