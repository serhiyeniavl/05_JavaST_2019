package com.epam.objects.factory;

import com.epam.objects.entity.Point;


public class PointFactoryImpl implements PointFactory {

    public Point createPoint(final double coordinateX,
                             final double coordinateY,
                             final double coordinateZ) {
        return new Point(coordinateX, coordinateY, coordinateZ);
    }
}
