package com.epam.objects.repository.specification.sort;

import com.epam.objects.action.Calculator;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.repository.specification.SortPyramidSpecification;

import java.util.Comparator;

public class SortByVolumeSpecification implements SortPyramidSpecification {
    @Override
    public Comparator<Pyramid> sort() {
        return Comparator.comparingDouble(
                value -> new Calculator().calcVolume(value));
    }
}
