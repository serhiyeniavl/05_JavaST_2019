package by.training.info_system.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
public class Passport extends Entity implements Serializable {
    private String serie;
    private Integer number;
    private String idNumber;
    private LocalDate issueDate;
    private LocalDate endDate;
}
