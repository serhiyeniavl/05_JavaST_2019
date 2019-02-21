package com.epam.objects.factory;

import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.InvalidDataAmountException;

import java.util.List;

/**
 * Class creator based on factory method.
 */
public class PyramidFactoryImpl implements PyramidFactory {

    /**
     * Creates object pyramid.
     *
     * @param points basis points.
     * @param height pyramid height.
     * @param angels number of angels.
     * @return new object {@link Pyramid}
     * @throws InvalidDataAmountException when list size does not suite for
     * program requirements.
     */
    public Pyramid createPyramid(final List<Point> points, final double height,
                                 final double angels)
            throws InvalidDataAmountException {
        return new Pyramid(points, height, angels);
    }
}
