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
    private String fName;
    private String lName;
    private String address;
    private Passport passport;
}
