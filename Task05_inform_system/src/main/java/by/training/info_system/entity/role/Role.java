package by.training.info_system.entity.role;

import java.io.Serializable;

public enum Role implements Serializable {
    UNAUTHORIZED_USER(0),
    USER(1),
    MANAGER(2),
    ADMIN(3);

    private Integer value;

    Role(final Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }

    public static Role fromValue(final Integer v) {
        for (Role r : Role.values()) {
            if (r.value.equals(v)) {
                return r;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }
}
