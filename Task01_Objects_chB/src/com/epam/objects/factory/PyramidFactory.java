package com.epam.objects.factory;

import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.InvalidDataAmountException;

import java.util.List;

/**
 * Factory method implementation for creation class {@link Pyramid}.
 */
public interface PyramidFactory extends Factory<Pyramid> {

    /**
     * @param points basis points.
     * @param height pyramid height.
     * @param angels number of angels.
     * @return pyramid constructor.
     * @throws InvalidDataAmountException when list size does not suite for
     * program requirements.
     */
    Pyramid createPyramid(List<Point> points,
                          double height, double angels)
            throws InvalidDataAmountException;
}
