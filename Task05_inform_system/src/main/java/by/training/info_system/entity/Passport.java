package by.training.info_system.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
public class Passport extends Entity implements Serializable {
    @NonNull private String serie;
    @NonNull private Integer number;
    @NonNull private String idNumber;
    @NonNull private Date issueDate;
    @NonNull private Date endDate;
}
