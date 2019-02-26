package com.epam.objects.repository.specification;

/**
 * Interface for find query to repository.
 */
@FunctionalInterface
public interface FindPyramidSpecification extends PyramidSpecification {
    /**
     * Method find objects in specified criteria.
     * @return {@code true} if object found.
     */
    boolean specified(Object object);
}
