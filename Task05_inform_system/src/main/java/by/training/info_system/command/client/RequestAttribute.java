package by.training.info_system.command.client;

public enum RequestAttribute {
    BLACK_LIST("blackList"),
    INCORRECT_DATA("incorrectData"),
    INFO("info"),
    ORDER_ID("order_id"),
    NUM_OF_PAGES("num_of_pages"),
    CURRENT_PAGE("current_page"),
    RECORD_PER_PAGE("records_per_page"),
    CARS("rental_cars"),
    ORDERS("car_orders"),
    SIGNED_UP("signedUp");

    private String value;

    RequestAttribute(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
