package com.epam.objects.factory;

import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;

import java.util.List;


public interface PyramidFactory extends Factory {

    Pyramid createPyramid(List<Point> points,
                          double height, double angels);
}
