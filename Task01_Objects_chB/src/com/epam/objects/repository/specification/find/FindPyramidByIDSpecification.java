package com.epam.objects.repository.specification.find;

import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.InvalidDataAmountException;
import com.epam.objects.repository.specification.FindPyramidSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for find {@link com.epam.objects.entity.Pyramid} by ID.
 */
public class FindPyramidByIDSpecification implements FindPyramidSpecification {
    /**
     * Logger for logging error in constructor.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FindPyramidByIDSpecification.class);

    /**
     * Required id.
     */
    private int id;

    /**
     * Constructor - sets required id.
     * @param pyramidID id for find.
     * @throws InvalidDataAmountException when id is invalid.
     */
    public FindPyramidByIDSpecification(final int pyramidID)
            throws InvalidDataAmountException {
        if (pyramidID < 0) {
            LOGGER.error("Illegal argument id(less then zero).");
            throw new InvalidDataAmountException("Negative number.");
        }
        this.id = pyramidID;
    }

    /**
     * Method check if required id equals to taken pyramid id.
     * @param object taken pyramid for compare ids.
     * @return {@code true} if ids are equal.
     */
    @Override
    public boolean specified(final Object object) {
        Pyramid pyramid = (Pyramid) object;
        return pyramid.getId() == id;
    }
}
