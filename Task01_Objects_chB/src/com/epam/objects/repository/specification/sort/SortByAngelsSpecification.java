package com.epam.objects.repository.specification.sort;

import com.epam.objects.entity.Pyramid;
import com.epam.objects.repository.specification.SortPyramidSpecification;

import java.util.Comparator;

/**
 * Class for sort pyramid by angels.
 */
public class SortByAngelsSpecification implements SortPyramidSpecification {
    /**
     * Method sorts pyramids by angels, if they are equal sorts by id.
     * @return {@link Comparator} that sorts pyramids by angels, in case of
     * coincidence sorts by id.
     */
    @Override
    public Comparator<Pyramid> sort() {
        return Comparator.comparing(Pyramid::getAngels)
                .thenComparing(Pyramid::getId);
    }
}
