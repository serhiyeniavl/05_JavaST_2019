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

/**
 * Class stores {@link Pyramid} and {@link PyramidRecorder} objects. Class
 * declares as singleton, it has only one object for entire program.
 */
public final class PyramidRepositorySingleton implements Observer {
    /**
     * Logger for logging errors.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(PyramidRepositorySingleton.class);

    /**
     * Single instance of the class.
     */
    private static final PyramidRepositorySingleton INSTANCE
            = new PyramidRepositorySingleton();

    /**
     * Pyramids storing in repo.
     */
    private List<Pyramid> pyramids;

    /**
     * Recorders for pyramids.
     */
    private List<PyramidRecorder> recorders;

    /**
     * Constructor - initializing lists.
     */
    private PyramidRepositorySingleton() {
        pyramids = new ArrayList<>();
        recorders = new ArrayList<>();
    }

    /**
     * @return instance of the class.
     */
    public static PyramidRepositorySingleton getInstance() {
        return INSTANCE;
    }

    /**
     * Method adds pyramid and recorder in repo.
     *
     * @param pyramid  for store.
     * @param recorder for store.
     * @throws NullArgumentException when pyramid or recorder is null.
     */
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

    /**
     * Delete pyramid and it's recorder by index.
     *
     * @param index of pyramid.
     * @throws InvalidDataAmountException when index is invalid.
     */
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

    /**
     * Delete pyramid and it's recorder by pyramid instance.
     *
     * @param pyramid for deleting.
     * @throws NullArgumentException when pyramid is null.
     */
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

    /**
     * @return pyramids store in repo.
     */
    public List<Pyramid> getAllPyramids() {
        return new ArrayList<>(pyramids);
    }

    /**
     * @return recorders store in repo.
     */
    public List<PyramidRecorder> getAllRecorders() {
        return new ArrayList<>(recorders);
    }

    /**
     * Takes users query by specified method. If specification is finding
     * method casts it to {@link FindPyramidSpecification}, otherwise to
     * {@link SortPyramidSpecification}.
     * @param specification specified query.
     * @return list based on specified query.
     */
    public List<Pyramid> query(final PyramidSpecification specification) {
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
            pyramidList = new ArrayList<>(pyramids);
            pyramidList.sort(sortSpecification.sort());
        }
        return pyramidList;
    }

    /**
     * Delete all objects storing in repo.
     */
    public void clearRepository() {
        recorders.clear();
        pyramids.clear();
    }

    /**
     * {@link com.epam.objects.observer.Observable} method for updating repo
     * objects that changed and notified all observers.
     * @param ob
     */
    @Override
    public void update(final Object ob) {
        Pyramid pyramid = (Pyramid) ob;
        int counter = 0;
        for (Pyramid p : pyramids) {
            if (p == pyramid) {
                pyramids.set(counter, pyramid);
            }
            ++counter;
        }
    }

    /**
     * Finds pyramid in repo by appropriate recorder id.
     * @param id recorder id.
     * @return {@link Optional} object of pyramid.
     */
    private Optional<Pyramid> findPyramidByRecorderId(final int id) {
        return Optional.of(pyramids.stream()
                .filter(pyramid -> pyramid.getId() == id).findFirst()).get();
    }
}
