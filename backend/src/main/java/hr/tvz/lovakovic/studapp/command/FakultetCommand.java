package hr.tvz.lovakovic.studapp.command;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class FakultetCommand {
    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NegativeOrZero(message = "Debt cannot be positive :)")
    private Integer debtAmount;
    @Future(message = "Next promotion date must be in the future")
    private LocalDate nextPromotionDate;
}
