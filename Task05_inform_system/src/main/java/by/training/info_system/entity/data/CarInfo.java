package by.training.info_system.entity.data;

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
public class CarInfo {
    @NonNull private Long carId;
    private String description;
    @NonNull private Short yearMade;
    @NonNull private Integer run;
    @NonNull private LocalDate lastMaintenance;
}
