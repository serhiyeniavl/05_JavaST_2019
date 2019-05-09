package by.training.info_system.entity;

import by.training.info_system.entity.data.CarInfo;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
public class Car extends Entity implements Serializable {
    private String brandName;
    private String description;
    private Short yearMade;
    private Short rentPrice;
    private Character carClass;
    private String imagePath;
    private CarInfo carInfo;
}
