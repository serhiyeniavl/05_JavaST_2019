package by.training.info_system.entity;

import by.training.info_system.entity.status.OrderStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public String showIssueDate() {
        return issueDate.format(FORMATTER);
    }

    public String showReturnDate() {
        return returnDate.format(FORMATTER);
    }

    public String showRealReturnDate() {
        return realReturnDate.format(FORMATTER);
    }
}
