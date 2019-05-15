package by.training.info_system.command.client;

public enum RequestAttribute {
    BLACK_LIST("blackList"),
    INCORRECT_DATA("incorrect_data"),
    ALL_USERS("all"),
    MANAGERS("managers"),
    CUSTOMERS("customers"),
    INFO("info"),
    ORDER_STATUS("order_status"),
    ORDER_ID("order_id"),
    USERS_LIST("userList"),
    NUM_OF_PAGES("num_of_pages"),
    CURRENT_PAGE("current_page"),
    RECORD_PER_PAGE("records_per_page"),
    CARS("rental_cars"),
    PROFILE("data"),
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
