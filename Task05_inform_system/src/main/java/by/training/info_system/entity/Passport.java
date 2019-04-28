package by.training.info_system.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
public class Passport extends Entity {
    @NonNull private String serie;
    @NonNull private Integer number;
    @NonNull private String idNumber;
    @NonNull private Date issueDate;
    @NonNull private Date endDate;
}
