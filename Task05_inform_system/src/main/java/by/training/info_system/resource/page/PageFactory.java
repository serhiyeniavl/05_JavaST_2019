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
    }

    private PageFactory() {
    }

    public static JspPage defineAndGet(final PageEnum uri) {
        return pages.get(uri);
    }
}
