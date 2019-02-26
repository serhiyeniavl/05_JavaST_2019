package com.epam.objects.repository.specification.sort;

import com.epam.objects.entity.Pyramid;
import com.epam.objects.repository.specification.SortPyramidSpecification;

import java.util.Comparator;

/**
 * Class provides method for sort pyramid by first y point.
 */
public class SortByFirstYPointSpecification implements
        SortPyramidSpecification {
    /**
     * @return {@link Comparator} sorts pyramids by its first y point.
     */
    @Override
    public Comparator<Pyramid> sort() {
        return Comparator.comparingDouble(
                value1 -> value1.getPoint(0).getY());
    }
}
