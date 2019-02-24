package com.epam.objects.repository.specification.sort;

import com.epam.objects.entity.Pyramid;
import com.epam.objects.repository.specification.SortPyramidSpecification;

public class SortByFirstYPointSpecification implements
        SortPyramidSpecification {
    @Override
    public int compare(Object o1, Object o2) {
        Pyramid pyramid1 = (Pyramid) o1;
        Pyramid pyramid2 = (Pyramid) o2;
        return Double.compare(pyramid1.getPoint(0).getY(),
                pyramid2.getPoint(0).getY());
    }
}
