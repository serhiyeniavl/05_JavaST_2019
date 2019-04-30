package by.training.info_system.entity.data;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
public class CarInfo implements Serializable {
    @NonNull private Long carId;
    private String description;
    @NonNull private Short yearMade;
    @NonNull private Integer run;
    @NonNull private Date lastMaintenance;
}
