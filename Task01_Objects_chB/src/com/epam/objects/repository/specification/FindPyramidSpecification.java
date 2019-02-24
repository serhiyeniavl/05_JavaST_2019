package com.epam.objects.repository.specification;

@FunctionalInterface
public interface FindPyramidSpecification extends PyramidSpecification {
    boolean specified(Object object);
}
