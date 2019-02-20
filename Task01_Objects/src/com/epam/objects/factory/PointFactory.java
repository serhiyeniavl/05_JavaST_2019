package com.epam.objects.factory;

import com.epam.objects.entity.Point;

/**
 * Factory method implementation for creation class {@link Point}.
 */
public interface PointFactory extends Factory {
    /**
     * @param coordinateX value x.
     * @param coordinateY value y.
     * @param coordinateZ value z.
     * @return point constructor.
     */
    Point createPoint(double coordinateX, double coordinateY,
                      double coordinateZ);
}
