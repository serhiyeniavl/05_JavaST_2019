package com.epam.objects.repository.specification.sort;

import com.epam.objects.entity.Pyramid;
import com.epam.objects.repository.specification.SortPyramidSpecification;

import java.util.Comparator;

public class SortByFirstYPointSpecification implements
        SortPyramidSpecification {
    @Override
    public Comparator<Pyramid> sort() {
        return Comparator.comparingDouble(
                value1 -> value1.getPoint(0).getY());
    }
}
