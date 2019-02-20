package com.epam.objects.factory;

import com.epam.objects.entity.Point;

/**
 * Class creator based on factory method.
 */
public class PointFactoryImpl implements PointFactory {

    /**
     * Creates object point.
     * @see Point
     * @param coordinateX value x.
     * @param coordinateY value y.
     * @param coordinateZ value z.
     * @return new object {@link Point}
     */
    public Point createPoint(final double coordinateX,
                             final double coordinateY,
                             final double coordinateZ) {
        return new Point(coordinateX, coordinateY, coordinateZ);
    }
}
