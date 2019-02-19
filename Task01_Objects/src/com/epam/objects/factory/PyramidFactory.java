package com.epam.objects.factory;

import com.epam.objects.entity.Geometry;
import com.epam.objects.entity.Point;

import java.util.List;

public interface PyramidFactory extends Factory {

    Geometry createPyramid(List<Point> points,
                           double height, double angels);
}
