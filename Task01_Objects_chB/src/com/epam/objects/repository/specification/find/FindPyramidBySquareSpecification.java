package com.epam.objects.repository.specification.find;

import com.epam.objects.registrator.PyramidRecorder;

public class FindPyramidBySquareSpecification implements
        FindPyramidByTechnicalCharacteristic {

    private double square1;
    private double square2;

    private FindPyramidBySquareSpecification() {
    }

    public FindPyramidBySquareSpecification(final double s1, final double s2) {
        this.square1 = s1;
        this.square2 = s2;
    }

    @Override
    public boolean specified(Object object) {
        PyramidRecorder recorder = (PyramidRecorder) object;
        return square1 < recorder.getSquare() && recorder.getSquare() < square2;
    }
}
