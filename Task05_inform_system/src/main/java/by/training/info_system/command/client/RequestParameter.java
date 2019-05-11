package by.training.info_system.command.client;

public enum RequestParameter {
    USER_NAME("nm"),
    ORDER_ID("oi"),
    TIME("tm"),
    USER_LOGIN("ul"),
    ATTRIBUTE("at"),
    SECURITY("sct"),
    MESSAGE("msg");

    private String value;

    public String getValue() {
        return value;
    }

    RequestParameter(final String value) {
        this.value = value;
    }
}
