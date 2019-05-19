package by.training.info_system.entity.data;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
public class CarInfo implements Serializable {
    private Long carId;
    private String vinCode;
    private String regNumber;
    private Integer run;
}
