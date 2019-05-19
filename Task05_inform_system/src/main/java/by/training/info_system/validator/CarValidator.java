package by.training.info_system.validator;

import by.training.info_system.entity.Car;

public class CarValidator implements Validator<Car> {
    private static final String YEAR_REGEX = "^[0-9]{4}$";
    private static final String PRICE_REGEX = "^[0-9]{1,}$";
    private static final String CLASS_AUTO_REGEX = "^[A-Z]$";
    private static final String VIN_CODE_REGEX
            = "^[0-9][A-Z]{4}[0-9]{2}[A-Z]{4}[0-9]{5}$";
    private static final String REG_NUMBER_REGEX = "^[0-9]{4}[A-Z]{2}[-][0-9]$";
    private static final String RUN_REGEX = "^[0-9]{1,}$";

    @Override
    public boolean validate(final Car object) {
        return object.getYearMade().toString().matches(YEAR_REGEX)
                && object.getRentPrice().toString().matches(PRICE_REGEX)
                && object.getCarClass().toString().matches(CLASS_AUTO_REGEX)
                && object.getCarInfo().getVinCode().matches(VIN_CODE_REGEX)
                && object.getCarInfo().getRegNumber().matches(REG_NUMBER_REGEX)
                && object.getCarInfo().getRun().toString().matches(RUN_REGEX);
    }
}
