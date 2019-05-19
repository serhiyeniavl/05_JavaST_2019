package by.training.info_system.entity.ban_reason;

public enum BanReason {
    EXPIRED_TIME("Didn't return a car in time.");

    private String value;

    BanReason(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static BanReason fromValue(final String v) {
        for (BanReason r : BanReason.values()) {
            if (r.value.equals(v)) {
                return r;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
