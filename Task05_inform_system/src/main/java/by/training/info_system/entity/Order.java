package by.training.info_system.entity;

import by.training.info_system.entity.status.OrderStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
public class Order extends Entity implements Serializable {
    private User user;
    private Car car;
    private OrderStatus status;
    private LocalDateTime issueDate;
    private LocalDateTime returnDate;
    private LocalDateTime realReturnDate;
    private Long finalPrice;
}
