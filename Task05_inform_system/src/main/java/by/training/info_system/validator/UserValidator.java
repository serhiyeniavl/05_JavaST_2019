package by.training.info_system.validator;

import by.training.info_system.entity.User;

public class UserValidator implements Validator<User> {
    private static final String EMAIL_REGEX = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";
    private static final String NAME_REGEX = "[A-Z][a-z]{1,30}";
    private static final String ADDRESS_REGEX = "(^[A-Z][A-Za-z]{1,20}," +
            "\\s[A-Z][A-Za-z]{1,30}\\s[0-9]{1,4}/[0-9]{1,5}$)";
    private static final String PASSPORT_NUM_REGEX_BLR = "^[A-Z]{2}[0-9]{7}$";
    private static final String PASSPORT_ID_NUM_REGEX_BLR = "^[0-9]{7}[A-Z]{1}" +
            "[0-9]{3}[A-Z]{2}[0-9]{1}$";

    @Override
    public boolean validate(final User object) {
        return object.getLogin().matches(EMAIL_REGEX)
                && object.getUserData().getFName().matches(NAME_REGEX)
                && object.getUserData().getLName().matches(NAME_REGEX)
                && object.getUserData().getAddress().matches(ADDRESS_REGEX)
                && object.getUserData().getPassport().getEndDate()
                    .isAfter(object.getUserData().getPassport().getIssueDate())
                && (object.getUserData().getPassport().getSerie()
                    .concat(object.getUserData().getPassport().getNumber().toString())
                    .matches(PASSPORT_NUM_REGEX_BLR))
                && object.getUserData().getPassport().getIdNumber()
                    .matches(PASSPORT_ID_NUM_REGEX_BLR);
    }
}
