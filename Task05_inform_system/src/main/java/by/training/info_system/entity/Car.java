package by.training.info_system.entity;

import by.training.info_system.entity.data.CarInfo;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
public class Car extends Entity {
    @NonNull private String vinCode;
    @NonNull private String brandName;
    @NonNull private String regNumber;
    @NonNull private Short rentPrice;
    @NonNull private Character carClass;
    private String imagePath;
    @NonNull private CarInfo carInfo;

}
