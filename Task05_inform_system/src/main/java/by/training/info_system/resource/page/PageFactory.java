package by.training.info_system.resource.page;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PageFactory {
    private static final Map<PageEnum, JspPage> pages = new ConcurrentHashMap<>();

    static {
        pages.put(PageEnum.HOME, new HomePage());
        pages.put(PageEnum.CARS, new CarsPage());
        pages.put(PageEnum.SIGNIN, new SignInPage());
        pages.put(PageEnum.SIGNUP, new SignUpPage());
        pages.put(PageEnum.CONTACT, new ContactPage());
        pages.put(PageEnum.MY_ORDERS, new UserOrdersPage());
        pages.put(PageEnum.ORDERS, new OrdersPage());
        pages.put(PageEnum.PROFILE, new ProfilePage());
        pages.put(PageEnum.USERS, new UsersPage());
    }

    private PageFactory() {
    }

    public static JspPage defineAndGet(final PageEnum uri) {
        clearParams();
        return pages.get(uri);
    }

    private static void clearParams() {
        pages.values().forEach(JspPage::clearRequestParameters);
    }
}
