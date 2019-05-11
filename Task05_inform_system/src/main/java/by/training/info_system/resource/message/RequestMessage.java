package by.training.info_system.resource.message;

import by.training.info_system.resource.RequestMessageManager;

public enum RequestMessage {
    INCORRECT_SIGNIN(RequestMessageManager.getInstance()
            .getProperty("incorrect_signin")),
    BANNED_ACCOUNT(RequestMessageManager.getInstance()
            .getProperty("banned_account")),
    INCORRECT_SIGNUP_FORM(RequestMessageManager.getInstance()
            .getProperty("incorrect_signup_form")),
    SUCCESSFUL_SIGNUP(RequestMessageManager.getInstance()
            .getProperty("successful_signup")),
    USER_WITH_PASSPORT_EXIST(RequestMessageManager.getInstance()
            .getProperty("user_with_passport_exist")),
    BANNED_ACCOUNT_EXIST(RequestMessageManager.getInstance()
            .getProperty("banned_account_exist")),
    MORE_SECURE(RequestMessageManager.getInstance()
            .getProperty("more_secure")),
    ORDER_WAIT(RequestMessageManager.getInstance()
            .getProperty("order_wait")),
    ORDER_GOES_WRONG(RequestMessageManager.getInstance()
            .getProperty("order_goes_wrong")),
    ACTIVE_ORDER(RequestMessageManager.getInstance()
            .getProperty("active_order")),
    SIGNIN_TO_RENT(RequestMessageManager.getInstance()
            .getProperty("signin_to_rent"));


    private String value;

    RequestMessage(final String val) {
        this.value = val;
    }

    public String getValue() {
        return value;
    }
}
