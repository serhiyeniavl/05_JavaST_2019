package by.training.info_system.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
public class Order extends Entity implements Serializable {
    private User user;
    private Car car;
    private Date issueDate;
    private Date returnDate;
    private LocalDate realReturnDate;
    private Long finalPrice;
}
