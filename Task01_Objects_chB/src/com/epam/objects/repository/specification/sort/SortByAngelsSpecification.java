package com.epam.objects.repository.specification.sort;

import com.epam.objects.entity.Pyramid;
import com.epam.objects.repository.specification.SortPyramidSpecification;

import java.util.Comparator;

public class SortByAngelsSpecification implements SortPyramidSpecification {
    @Override
    public Comparator<Pyramid> sort() {
        return Comparator.comparing(Pyramid::getAngels)
                .thenComparing(Pyramid::getId);
    }
}
