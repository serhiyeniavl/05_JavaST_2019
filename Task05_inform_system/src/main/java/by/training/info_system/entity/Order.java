package by.training.info_system.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
public class Order extends Entity {
    @NonNull private User user;
    @NonNull private Car car;
    @NonNull private LocalDate issueDate;
    @NonNull private LocalDate returnDate;
    private LocalDate realReturnDate;
    private Long finalPrice;
}
