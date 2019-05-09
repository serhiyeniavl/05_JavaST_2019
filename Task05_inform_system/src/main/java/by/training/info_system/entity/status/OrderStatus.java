package by.training.info_system.entity.status;

public enum OrderStatus {
    NOT_CONFIRMED("Not confirmed"),
    EXPIRED("Expired"),
    CONFIRMED("Confirmed");

    private String value;

    OrderStatus(final String val) {
        this.value = val;
    }

    public String getValue() {
        return value;
    }

    public static OrderStatus fromValue(final String v) {
        for (OrderStatus r : OrderStatus.values()) {
            if (r.value.equals(v)) {
                return r;
            }
        }
        throw new IllegalArgumentException(v);
    }

    @Override
    public String toString() {
        return value;
    }
}
