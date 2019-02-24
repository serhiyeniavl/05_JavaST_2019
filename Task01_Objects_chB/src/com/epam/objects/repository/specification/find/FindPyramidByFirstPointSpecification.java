package com.epam.objects.repository.specification.find;

import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.NullArgumentException;
import com.epam.objects.repository.specification.FindPyramidSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindPyramidByFirstPointSpecification implements
        FindPyramidSpecification {
    private static final Logger LOGGER
            = LogManager.getLogger(FindPyramidByFirstPointSpecification.class);

    private Point point;

    private FindPyramidByFirstPointSpecification() {
    }

    public FindPyramidByFirstPointSpecification(final Point findingPoint)
            throws NullArgumentException {
        if (findingPoint == null) {
            LOGGER.error("Argument point is null.");
            throw new NullArgumentException("Method argument is null.");
        }
        this.point = findingPoint;
    }

    @Override
    public boolean specified(Object object) {
        Pyramid pyramid = (Pyramid) object;
        return point.equals(pyramid.getPoint(0));
    }
}
