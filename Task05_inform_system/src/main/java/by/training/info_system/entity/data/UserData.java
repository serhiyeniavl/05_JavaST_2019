package by.training.info_system.entity.data;

import by.training.info_system.entity.Passport;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class UserData implements Serializable {
    @NonNull private String fName;
    @NonNull private String lName;
    @NonNull private String address;
    @NonNull private Passport passport;
}
