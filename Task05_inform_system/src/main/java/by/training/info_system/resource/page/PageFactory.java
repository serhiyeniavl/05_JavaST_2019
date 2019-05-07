package by.training.info_system.resource.page;

public final class PageFactory {

    private PageFactory() {
    }

    public static JspPage defineAndGet(final PageEnum uri) {
        if (uri.getUri().equals("home")) {
            return new HomePage();
        }
        if (uri.getUri().equals("signin")) {
            return new SignInPage();
        }
        if (uri.getUri().equals("signup")) {
            return new SignUpPage();
        }
        if (uri.getUri().equals("contact")) {
            return new ContactPage();
        }
        if (uri.getUri().equals("cars")) {
            return new CarsPage();
        }
        throw new IllegalStateException("Unknown uri: /" + uri);
    }
}
