package com.epam.objects.repository.specification.sort;

import com.epam.objects.entity.Pyramid;
import com.epam.objects.registrator.PyramidRecorder;
import com.epam.objects.repository.specification.SortPyramidSpecification;

public class SortBySquareSpecification implements SortPyramidSpecification {
    @Override
    public int compare(Object o1, Object o2) {
        Pyramid pyramid1 = (Pyramid) o1;
        Pyramid pyramid2 = (Pyramid) o2;
        PyramidRecorder recorder1 = new PyramidRecorder();
        PyramidRecorder recorder2 = new PyramidRecorder();
        recorder1.register(pyramid1);
        recorder2.register(pyramid2);
        return Double.compare(recorder1.getSquare(), recorder2.getSquare());
    }
}
