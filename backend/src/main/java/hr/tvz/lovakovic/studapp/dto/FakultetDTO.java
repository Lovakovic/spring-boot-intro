package hr.tvz.lovakovic.studapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class FakultetDTO {
    private String name;
    private String address;
    private LocalDate nextPromotionDate;
}
