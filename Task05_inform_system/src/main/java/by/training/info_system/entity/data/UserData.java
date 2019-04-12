package by.training.info_system.entity.data;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserData {
    @NonNull private Long userId;
    @NonNull private String fName;
    @NonNull private String lName;
    @NonNull private String passportData;
    @NonNull private Integer ordersQuantity;
    @NonNull private Integer addressId;
}
