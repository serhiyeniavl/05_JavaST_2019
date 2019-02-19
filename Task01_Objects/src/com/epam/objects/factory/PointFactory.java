package com.epam.objects.factory;

import com.epam.objects.entity.Point;


public interface PointFactory extends Factory {
    Point createPoint(double coordinateX, double coordinateY,
                      double coordinateZ);
}
