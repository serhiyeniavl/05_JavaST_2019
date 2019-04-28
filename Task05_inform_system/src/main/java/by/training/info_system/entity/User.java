package by.training.info_system.entity;

import by.training.info_system.entity.data.UserData;
import by.training.info_system.entity.role.Role;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
public class User extends Entity {
    @NonNull private String login;
    @NonNull private String password;
    @NonNull private Role role;
    private UserData userData;
}
