package com.epam.objects.factory;

import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;

import java.util.List;

/**
 * Factory method implementation for creation class {@link Pyramid}.
 */
public interface PyramidFactory extends Factory {

    /**
     * @param points basis points.
     * @param height pyramid height.
     * @param angels number of angels.
     * @return pyramid constructor.
     */
    Pyramid createPyramid(List<Point> points,
                          double height, double angels);
}
