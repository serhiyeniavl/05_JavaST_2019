package com.epam.objects.repository.specification.find;

import com.epam.objects.registrator.PyramidRecorder;

/**
 * Class for find {@link com.epam.objects.entity.Pyramid} by volume.
 */
public class FindPyramidByVolumeSpecification implements
        FindPyramidByTechnicalCharacteristic {

    /**
     * Left border value.
     */
    private double volume1;

    /**
     * Right border value.
     */
    private double volume2;

    /**
     * Constructor - initializing values.
     * @param v1 volume left border.
     * @param v2 volume right border.
     */
    public FindPyramidByVolumeSpecification(final double v1, final double v2) {
        this.volume1 = v1;
        this.volume2 = v2;
    }

    /**
     * Method defines {@link com.epam.objects.entity.Pyramid} volume in
     * specified borders.
     * @param object pyramid.
     * @return {@code true} if pyramid volume is in specified scopes.
     */
    @Override
    public boolean specified(final Object object) {
        PyramidRecorder recorder = (PyramidRecorder) object;
        return volume1 < recorder.getVolume() && recorder.getVolume() < volume2;
    }
}
