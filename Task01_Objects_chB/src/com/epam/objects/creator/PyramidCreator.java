package com.epam.objects.creator;

import com.epam.objects.constants.FileData;
import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.InvalidDataAmountException;
import com.epam.objects.factory.Factory;
import com.epam.objects.factory.PointFactoryImpl;
import com.epam.objects.factory.PyramidFactoryImpl;
import com.epam.objects.validator.ObjectValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class developed for creating object {@link Pyramid}.
 * @see Point
 * @see ObjectValidator
 */
public class PyramidCreator {

    /**
     * Creates object of {@link Pyramid}, using {@link ObjectValidator} for
     * validating data.
     * @param inputData data for creating.
     * @return {@link List} of created com.epam.test.com.epam.objects.
     * @throws InvalidDataAmountException when constructor of {@link Pyramid}
     * got invalid data.
     */
    public List<Pyramid> create(final Map<Integer, List<Double>> inputData)
            throws InvalidDataAmountException {
        ObjectValidator validator;
        List<Pyramid> pyramids = new ArrayList<>();
        Factory<Point> pointFactory = new PointFactoryImpl();
        Factory<Pyramid> pyramidFactory = new PyramidFactoryImpl();
        for (int i = 0; i < inputData.size(); i++) {
            List<Double> data = inputData.get(i);
            validator = new ObjectValidator(data);
            if (validator.validate()) {
                Point point1 = ((PointFactoryImpl) pointFactory).createPoint(
                        data.get(FileData.FIRST_X.getPosition()),
                        data.get(FileData.FIRST_Y.getPosition()),
                        data.get(FileData.FIRST_Z.getPosition()));
                Point point2 = ((PointFactoryImpl) pointFactory).createPoint(
                        data.get(FileData.SECOND_X.getPosition()),
                        data.get(FileData.SECOND_Y.getPosition()),
                        data.get(FileData.SECOND_Z.getPosition()));
                List<Point> points = new ArrayList<>();
                points.add(point1);
                points.add(point2);
                pyramids.add(((PyramidFactoryImpl) pyramidFactory).
                        createPyramid(points, data.get(FileData.HEIGHT
                                .getPosition()), data.get(FileData
                                .NUM_OF_ANGELS.getPosition())));
            }
        }
        return pyramids;
    }
}
