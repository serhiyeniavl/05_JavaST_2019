package com.epam.objects.repository.specification;

/**
 * Interface for find query to repository.
 */
@FunctionalInterface
public interface FindPyramidSpecification extends PyramidSpecification {
    /**
     * Method find objects in specified criteria.
     * @return {@code true} if object found.
     * @param object object for check it by criteria.
     */
    boolean specified(Object object);
}
