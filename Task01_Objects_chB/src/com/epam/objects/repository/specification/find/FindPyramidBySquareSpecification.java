package com.epam.objects.repository.specification.find;

import com.epam.objects.registrator.PyramidRecorder;

/**
 * Class for find {@link com.epam.objects.entity.Pyramid} by volume.
 */
public class FindPyramidBySquareSpecification implements
        FindPyramidByTechnicalCharacteristic {

    /**
     * Left border value.
     */
    private double square1;

    /**
     * Right border value.
     */
    private double square2;

    /**
     * Constructor - initializing values.
     * @param s1 square left border.
     * @param s2 squre right border.
     */
    public FindPyramidBySquareSpecification(final double s1, final double s2) {
        this.square1 = s1;
        this.square2 = s2;
    }

    /**
     * Method defines {@link com.epam.objects.entity.Pyramid} square in
     * specified borders.
     * @param object pyramid.
     * @return {@code true} if pyramid square is in specified scopes.
     */
    @Override
    public boolean specified(final Object object) {
        PyramidRecorder recorder = (PyramidRecorder) object;
        return square1 < recorder.getSquare() && recorder.getSquare() < square2;
    }
}
