package com.epam.objects.repository.specification.find;

import com.epam.objects.registrator.PyramidRecorder;

public class FindPyramidByVolumeSpecification implements
        FindPyramidByTechnicalCharacteristic {

    private double volume1;
    private double volume2;

    private FindPyramidByVolumeSpecification() {

    }

    public FindPyramidByVolumeSpecification(final double v1, final double v2) {
        this.volume1 = v1;
        this.volume2 = v2;
    }

    @Override
    public boolean specified(Object object) {
        PyramidRecorder recorder = (PyramidRecorder) object;
        return volume1 < recorder.getVolume() && recorder.getVolume() < volume2;
    }
}
