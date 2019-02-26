package com.epam.objects.repository.specification.sort;

import com.epam.objects.action.Calculator;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.repository.specification.SortPyramidSpecification;

import java.util.Comparator;

/**
 * Class provides method for sort pyramid by square.
 */
public class SortBySquareSpecification implements SortPyramidSpecification {
    /**
     * @return {@link Comparator} sorts pyramids by its square.
     */
    @Override
    public Comparator<Pyramid> sort() {
        return Comparator.comparingDouble(
                value -> new Calculator().calcSquare(value));
    }
}


