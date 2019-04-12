package by.training.info_system.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Car extends Entity {
    @NonNull private String vinCode;
    @NonNull private String brandName;
    private String description;
    @NonNull private String regNumber;
    @NonNull private Short yearMade;
    @NonNull private Integer run;
    @NonNull private LocalDate lastMaintenance;
    @NonNull private Short rentPrice;
    @NonNull private Integer mechanicCode;
    @NonNull private Character carClass;
    private String imagePath;

}
