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
    private String serie;
    private Integer number;
    private String idNumber;
    private Date issueDate;
    private Date endDate;
}
