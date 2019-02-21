package com.epam.objects.validator;

import com.epam.objects.constants.FileData;

import java.util.Arrays;
import java.util.List;

/**
 * Class for check when program can create
 * {@link com.epam.objects.entity.Pyramid} from {@link List}.
 * @see FileData
 */
public class ObjectValidator {
    /**
     * Data for validate.
     */
    private List<Double> data;

    /**
     * Private constructor.
     */
    private ObjectValidator() {
    }

    /**
     * Constructor - set a data for validate.
     * @param inputData data for validate.
     */
    public ObjectValidator(final List<Double> inputData) {
        data = inputData;
    }

    /**
     * Method for validating object.
     * @return {@code true} when can create object, {@code false} when can not.
     */
    public boolean validate() {
        if (isDontEnoughData(data)) {
            return false;
        }
        double height = data.get(FileData.HEIGHT.getPosition());
        if (isHeightIncorrect(height)) {
            return false;
        }
        if (isPointsDot(Arrays.asList(data.get(FileData.FIRST_X.getPosition()),
                data.get(FileData.FIRST_Y.getPosition()),
                data.get(FileData.SECOND_X.getPosition()),
                data.get(FileData.SECOND_Y.getPosition())))) {
            return false;
        }
        if (isLittleHeightIncorrect(data.get(FileData.LITTLE_HEIGHT
                .getPosition()), data.get(FileData.HEIGHT
                .getPosition()))) {
            return false;
        }
        if (isZNotEquals(data.get(FileData.FIRST_Z.getPosition()),
                data.get(FileData.SECOND_Z.getPosition()))) {
            return false;
        }
        return !isAngelsQuanIncorrect(data.get(FileData.NUM_OF_ANGELS
                .getPosition()));
    }

    /**
     * @param inputData data for check.
     * @return {@code true} when quantity of data is not enough, otherwise
     * {@code false}.
     */
    private boolean isDontEnoughData(final List<Double> inputData) {
        return inputData.size() != FileData.DATA_QUANTITY.getPosition();
    }

    /**
     * @param height pyramid height.
     * @return {@code true} when height length is not correct,
     * {@code false} when is correct.
     */
    private boolean isHeightIncorrect(final double height) {
        return Double.compare(height, 0) <= 0;
    }

    /**
     * @param littleHeight top pyramid height.
     * @param height all pyramid height.
     * @return {@code true} when top pyramid height is not correct, otherwise
     * return {@code false}.
     */
    private boolean isLittleHeightIncorrect(final double littleHeight,
                                            final double height) {
        return Double.compare(littleHeight, 0) <= 0
                || Double.compare(littleHeight, height) >= 0;
    }

    /**
     * @param angels number of basis angels.
     * @return {@code true} when quantity of angels do not create a basis,
     * otherwise {@code false}.
     */
    private boolean isAngelsQuanIncorrect(final double angels) {
        return angels != (int) angels && Double.compare(angels, 2) <= 0;
    }

    /**
     * @param points basis coordinate of points.
     * @return {@code true} when points create a dot, otherwise {@code false}.
     */
    private boolean isPointsDot(final List<Double> points) {
        final int firstXpos = 0;
        final int firstYpos = 1;
        final int secondXpos = 2;
        final int secondYpos = 3;
        return Double.compare(points.get(firstXpos), points.get(secondXpos))
                == 0 && Double.compare(points.get(firstYpos),
                points.get(secondYpos)) == 0;
    }

    /**
     * @param z1 coordinate z first point.
     * @param z2 coordanate z second point.
     * @return {@code true} when points do not arrange in plane parallel x, y
     * plane.
     */
    private boolean isZNotEquals(final double z1, final double z2) {
        return Double.compare(z1, z2) != 0;
    }
}
