package by.training.info_system.entity;

import lombok.NonNull;

public class Address extends Entity {
    @NonNull private String city;
    @NonNull private String street;
}
