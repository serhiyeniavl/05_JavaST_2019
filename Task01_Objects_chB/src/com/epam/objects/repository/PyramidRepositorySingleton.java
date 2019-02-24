package com.epam.objects.repository;

import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.InvalidDataAmountException;
import com.epam.objects.exception.NullArgumentException;
import com.epam.objects.observer.Observer;
import com.epam.objects.registrator.PyramidRecorder;
import com.epam.objects.repository.specification.FindPyramidSpecification;
import com.epam.objects.repository.specification.PyramidSpecification;
import com.epam.objects.repository.specification.SortPyramidSpecification;
import com.epam.objects.repository.specification.find.FindPyramidByTechnicalCharacteristic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PyramidRepositorySingleton implements Observer {
    private static final Logger LOGGER
            = LogManager.getLogger(PyramidRepositorySingleton.class);

    private static final PyramidRepositorySingleton INSTANCE
            = new PyramidRepositorySingleton();

    private List<Pyramid> pyramids;
    private List<PyramidRecorder> recorders;

    private PyramidRepositorySingleton() {
        pyramids = new ArrayList<>();
        recorders = new ArrayList<>();
    }

    public static PyramidRepositorySingleton getInstance() {
        return INSTANCE;
    }


    public void save(final Pyramid pyramid,
                     final PyramidRecorder recorder)
            throws NullArgumentException {
        if (pyramid == null || recorder == null) {
            LOGGER.error("Argument is null. Method can't add to"
                    + " list");
            throw new NullArgumentException("Method argument is null");
        }
        pyramids.add(pyramid);
        recorders.add(recorder);
    }

    public void delete(final int index) throws InvalidDataAmountException {
        if (index >= pyramids.size() || index < 0) {
            LOGGER.error("Index is invalid.");
            throw new InvalidDataAmountException("Index is out of bound.");
        }
        int id = pyramids.get(index).getId();
        pyramids.remove(index);
        for (PyramidRecorder recorder : recorders) {
            if (recorder.getId() == id) {
                recorders.remove(recorder);
                break;
            }
        }
    }

    public void delete(final Pyramid pyramid) throws NullArgumentException {
        if (pyramid == null) {
            LOGGER.error("Argument pyramid is null. Method can't delete"
                    + " elem");
            throw new NullArgumentException("Method argument is null");
        }
        int id = pyramid.getId();
        for (Pyramid p : pyramids) {
            if (p == pyramid) {
                pyramids.remove(pyramid);
                break;
            }
        }
        for (PyramidRecorder recorder : recorders) {
            if (recorder.getId() == id) {
                recorders.remove(recorder);
                break;
            }
        }
    }

    public List<Pyramid> getAllPyramids() {
        return new ArrayList<>(pyramids);
    }

    public List<PyramidRecorder> getAllRecorders() {
        return new ArrayList<>(recorders);
    }

    public List<Pyramid> query(PyramidSpecification specification) {
        List<Pyramid> pyramidList = new ArrayList<>();

        if (specification instanceof FindPyramidSpecification) {
            if (specification instanceof FindPyramidByTechnicalCharacteristic) {
                FindPyramidByTechnicalCharacteristic findTechSpecification
                        = (FindPyramidByTechnicalCharacteristic) specification;
                for (PyramidRecorder recorder : recorders) {
                    if (findTechSpecification.specified(recorder)) {
                        final int id = recorder.getId();
                        Optional<Pyramid> pyramid = findPyramidByRecorderId(id);
                        if (pyramid.isPresent()) {
                            pyramidList.add(pyramid.get());
                        }
                    }
                }
            } else {
                FindPyramidSpecification findSpecification
                        = (FindPyramidSpecification) specification;
                for (Pyramid pyramid : pyramids) {
                    if (findSpecification.specified(pyramid)) {
                        pyramidList.add(pyramid);
                    }
                }
            }
        } else if (specification instanceof SortPyramidSpecification) {
            SortPyramidSpecification sortSpecification
                    = (SortPyramidSpecification) specification;
            pyramidList = pyramids;
            pyramidList.sort(sortSpecification);
        }
        return pyramidList;
    }

    @Override
    public void update(Object ob) {
        Pyramid pyramid = (Pyramid) ob;
        int counter = 0;
        for (Pyramid p : pyramids) {
            if (p == pyramid) {
                pyramids.set(counter, pyramid);
            }
            ++counter;
        }
    }

    private Optional<Pyramid> findPyramidByRecorderId(final int id) {
        for (Pyramid pyramid : pyramids) {
            if (pyramid.getId() == id) {
                return Optional.of(pyramid);
            }
        }
        return Optional.empty();
    }
}
