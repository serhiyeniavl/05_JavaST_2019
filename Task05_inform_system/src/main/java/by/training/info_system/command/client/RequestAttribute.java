package by.training.info_system.command.client;

public enum RequestAttribute {
    BLACK_LIST("blackList"),
    INCORRECT_DATA("incorrectData"),
    SIGNED_UP("signedUp");

    private String value;

    RequestAttribute(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
