package by.training.info_system.resource.page;

public enum PageEnum {
    HOME("home"),
    SIGNIN("signin"),
    SIGNUP("signup"),
    CONTACT("contact"),
    CARS("cars");

    private String uri;

    PageEnum(final String pageUri) {
        uri = pageUri;
    }

    public String getUri() {
        return uri;
    }
}
