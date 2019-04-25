package by.training.info_system.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
public class Passport extends Entity {
    @NonNull private String serie;
    @NonNull private Integer number;
    @NonNull private String idNumber;
    @NonNull private LocalDate issueDate;
    @NonNull private LocalDate endDate;
}
