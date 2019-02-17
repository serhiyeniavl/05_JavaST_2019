package com.epam.objects.validator;

import com.epam.objects.constants.FileData;

import java.util.Arrays;
import java.util.List;

public class ObjectValidator {
    private List<Double> data;

    public ObjectValidator(final List<Double> inputData) {
        data = inputData;
    }

    public boolean validate() {
        if (isDontEnoughData(data)) {
            return false;
        }
        double apothem = data.get(FileData.APOTHEM.getPosition());
        if (isApothemIncorrect(apothem)) {
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
        if (isAngelsQuanIncorrect(data.get(FileData.NUM_OF_ANGELS
                .getPosition()))) {
            return false;
        }
        return !isApothemDoesNotCorrespondsHeight(data.get(FileData.HEIGHT
                .getPosition()), data.get(FileData.APOTHEM
                .getPosition()), data.get(FileData.NUM_OF_ANGELS
                .getPosition()));
    }

    private boolean isDontEnoughData(final List<Double> inputData) {
        return inputData.size() != FileData.DATA_QUANTITY.getPosition();
    }

    private boolean isApothemIncorrect(final double apothem) {
        return apothem <= 0.0;
    }

    private boolean isHeightIncorrect(final double height) {
        return height <= 0.0;
    }

    private boolean isLittleHeightIncorrect(final double littleHeight,
                                            final double height) {
        return littleHeight <= 0.0 || littleHeight >= height;
    }

    private boolean isAngelsQuanIncorrect(final double angels) {
        return angels != (int) angels && angels <= 2;
    }

    private boolean isPointsDot(final List<Double> points) {
        return (points.get(FileData.FIRST_X.getPosition())
                .equals(points.get(FileData.SECOND_X.getPosition())))
                && (points.get(FileData.FIRST_Y.getPosition())
                .equals(points.get(FileData.SECOND_Y.getPosition())));
    }

    private boolean isApothemDoesNotCorrespondsHeight(final double height,
                                                      final double apothem,
                                                      final double angels) {
        final double epsilon = 0.001;
        final double halfCircleGrad = 180;
        double insideRadius
                = Math.sqrt(((data.get(FileData.SECOND_X.getPosition())
                - data.get(FileData.FIRST_X.getPosition()))
                * (data.get(FileData.SECOND_X.getPosition())
                - data.get(FileData.FIRST_X.getPosition())))
                + ((data.get(FileData.SECOND_Y.getPosition())
                - data.get(FileData.FIRST_Y.getPosition()))
                * (data.get(FileData.SECOND_Y.getPosition())
                - data.get(FileData.FIRST_Y.getPosition()))));
        return apothem <= height || (Math.abs(((apothem * apothem)
                - (height * height)) - (Math.pow(insideRadius
                / (2 * Math.tan(Math.toRadians(halfCircleGrad / angels))), 2)))
                > epsilon);
    }
}
