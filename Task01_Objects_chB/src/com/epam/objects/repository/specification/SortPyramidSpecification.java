package com.epam.objects.repository.specification;

import com.epam.objects.entity.Pyramid;

import java.util.Comparator;

/**
 * Interface for sort query to repository.
 */
@FunctionalInterface
public interface SortPyramidSpecification extends PyramidSpecification {
    /**
     * Method sorts objects in specified order.
     * @return {@link Comparator} object that defined by query.
     */
    Comparator<Pyramid> sort();
}
