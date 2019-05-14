package by.training.info_system.resource.page;

public enum PageEnum {
    HOME("home"),
    SIGNIN("signin"),
    SIGNUP("signup"),
    CONTACT("contact"),
    ORDERS("orders"),
    MY_ORDERS("my_orders"),
    PROFILE("profile"),
    CARS("cars");

    private String uri;

    PageEnum(final String pageUri) {
        uri = pageUri;
    }

    public String getUri() {
        return uri;
    }
}
