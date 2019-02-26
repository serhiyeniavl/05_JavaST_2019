package com.epam.objects.repository.specification.sort;

import com.epam.objects.action.Calculator;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.repository.specification.SortPyramidSpecification;

import java.util.Comparator;

/**
 * Class provides method for sort pyramid by volume.
 */
public class SortByVolumeSpecification implements SortPyramidSpecification {
    /**
     * @return {@link Comparator} sorts pyramids by its volume.
     */
    @Override
    public Comparator<Pyramid> sort() {
        return Comparator.comparingDouble(
                value -> new Calculator().calcVolume(value));
    }
}
