package com.epam.objects.repository.specification.find;

import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.NullArgumentException;
import com.epam.objects.repository.specification.FindPyramidSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for find {@link com.epam.objects.entity.Pyramid} by second point.
 */
public class FindPyramidBySecondPointSpecification implements
        FindPyramidSpecification {
    /**
     * Logger for logging error in constructor.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FindPyramidBySecondPointSpecification.class);

    /**
     * Required point.
     */
    private Point point;

    /**
     * Constructor - initializing required point.
     * @param findingPoint point to find.
     * @throws NullArgumentException when point is null.
     */
    public FindPyramidBySecondPointSpecification(final Point findingPoint)
            throws NullArgumentException {
        if (findingPoint == null) {
            LOGGER.error("Argument point is null.");
            throw new NullArgumentException("Method argument is null.");
        }
        this.point = findingPoint;
    }

    /**
     * Method defines points coincidence.
     * @param object pyramid where can get a point.
     * @return {@code true} if points are equal.
     */
    @Override
    public boolean specified(final Object object) {
        Pyramid pyramid = (Pyramid) object;
        return point.equals(pyramid.getPoint(1));
    }
}
