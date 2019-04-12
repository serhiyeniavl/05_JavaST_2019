package by.training.info_system.entity;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Address extends Entity {
    @NonNull private String city;
    @NonNull private String street;
}
