package by.training.info_system.entity;

import by.training.info_system.entity.data.UserData;
import by.training.info_system.entity.role.Role;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
public class User extends Entity implements Serializable {
    private String email;
    private String password;
    private Role role;
    private UserData userData;
}
