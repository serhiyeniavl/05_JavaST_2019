package com.epam.objects.repository.specification;

import com.epam.objects.entity.Pyramid;

import java.util.Comparator;


public interface SortPyramidSpecification extends PyramidSpecification {
    Comparator<Pyramid> sort();
}
