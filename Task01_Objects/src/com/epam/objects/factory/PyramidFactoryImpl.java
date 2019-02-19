package com.epam.objects.factory;

import com.epam.objects.entity.Geometry;
import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;

import java.util.List;

public class PyramidFactoryImpl implements PyramidFactory {

    public Geometry createPyramid(final List<Point> points, final double height,
                                  final double angels) {
        return new Pyramid(points, height, angels);
    }
}
