package by.training.info_system.entity.data;

import by.training.info_system.entity.Passport;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class UserData {
    @NonNull private Long userId;
    @NonNull private String fName;
    @NonNull private String lName;
    @NonNull private String address;
    @NonNull private Passport passport;
}
