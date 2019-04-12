package by.training.info_system.entity.role;

public enum Role {
    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Role fromValue(final String v) {
        for (Role r : Role.values()) {
            if (r.value.equalsIgnoreCase(v)) {
                return r;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
