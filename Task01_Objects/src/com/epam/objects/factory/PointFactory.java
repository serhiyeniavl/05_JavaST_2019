package com.epam.objects.factory;

import com.epam.objects.entity.Geometry;


public interface PointFactory extends Factory {
    Geometry createPoint(double coordinateX, double coordinateY,
                         double coordinateZ);
}
