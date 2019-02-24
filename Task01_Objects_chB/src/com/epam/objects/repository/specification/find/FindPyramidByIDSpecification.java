package com.epam.objects.repository.specification.find;

import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.InvalidDataAmountException;
import com.epam.objects.repository.specification.FindPyramidSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindPyramidByIDSpecification implements FindPyramidSpecification {
    private static final Logger LOGGER
            = LogManager.getLogger(FindPyramidByIDSpecification.class);

    private int id;

    private FindPyramidByIDSpecification() {

    }

    public FindPyramidByIDSpecification(final int pyramidID)
            throws InvalidDataAmountException {
        if (pyramidID < 0) {
            LOGGER.error("Illegal argument id(less then zero).");
            throw new InvalidDataAmountException("Negative number.");
        }
        this.id = pyramidID;
    }

    @Override
    public boolean specified(Object object) {
        Pyramid pyramid = (Pyramid) object;
        return pyramid.getId() == id;
    }
}
