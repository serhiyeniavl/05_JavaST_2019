package com.epam.objects.repository.specification.sort;

import com.epam.objects.entity.Pyramid;
import com.epam.objects.repository.specification.SortPyramidSpecification;

import java.util.Comparator;

/**
 * Class provides method for sort pyramid by first x point.
 */
public class SortByFirstXPointSpecification implements
        SortPyramidSpecification {
    /**
     * @return {@link Comparator} sorts pyramids by its first x point.
     */
    @Override
    public Comparator<Pyramid> sort() {
        return Comparator.comparingDouble(
                value -> value.getPoint(0).getX());
    }
}
