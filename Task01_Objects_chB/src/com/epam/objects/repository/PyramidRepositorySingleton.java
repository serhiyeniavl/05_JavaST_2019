package com.epam.objects.repository;

import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.InvalidDataAmountException;
import com.epam.objects.exception.NullArgumentException;
import com.epam.objects.observer.Observer;
import com.epam.objects.registrator.PyramidRecorder;
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

    public void save(final Pyramid pyramid) throws NullArgumentException {
        if (pyramid == null) {
            LOGGER.error("Argument pyramid is null. Method can't add to"
            + " list");
            throw new NullArgumentException("Method argument is null");
        }
        pyramids.add(pyramid);
        PyramidRecorder recorder = new PyramidRecorder();
        recorder.register(pyramid);
        recorders.add(recorder);
    }

    public void delete(final int index) throws InvalidDataAmountException {
        if (index > pyramids.size() || index < 0) {
            LOGGER.error("Index is invalid.");
            throw new InvalidDataAmountException("Index is out of bound.");
        }
        pyramids.remove(index);
        recorders.remove(index);
    }

    public void delete(final Pyramid pyramid) throws NullArgumentException {
        if (pyramid == null) {
            LOGGER.error("Argument pyramid is null. Method can't delete"
                    + " elem");
            throw new NullArgumentException("Method argument is null");
        }
        int counter = 0;
        for (Pyramid p : pyramids) {
            if(p == pyramid) {
                pyramids.remove(pyramid);
                recorders.remove(counter);
            }
            ++counter;
        }
    }

    public List<Pyramid> getAllPyramids() {
        return new ArrayList<>(pyramids);
    }

    public List<PyramidRecorder> getAllRecorders() {
        return new ArrayList<>(recorders);
    }

    @Override
    public void update(Object ob) {
        Pyramid pyramid = (Pyramid)ob;
        int counter = 0;
        for (Pyramid p : pyramids) {
            if(p == pyramid) {
                pyramids.set(counter, pyramid);
                recorders.get(counter).update(ob);
            }
            ++counter;
        }
    }

//    public static void main(String[] args) throws InvalidDataAmountException,
//            NullArgumentException {
//
//        List<Point> points = new ArrayList<>() {
//            {
//                add(new Point(3, 3, 1));
//                add(new Point(3, 5, 1));
//            }
//        };
//        Pyramid pyramid1 = new Pyramid(points, 4, 5);
//        Pyramid pyramid2 = new Pyramid(points, 6, 5);
//
//        PyramidRepositorySingleton repository = PyramidRepositorySingleton.getInstance();
//
//        repository.save(pyramid1);
//        repository.save(pyramid2);
//        pyramid1.addObserver(repository);
//        pyramid2.addObserver(repository);
//
//        System.out.println(repository.getAllRecorders().get(0).getVolume());
//        System.out.println(repository.getAllRecorders().get(1).getVolume());
//
//        pyramid1.setHeight(7);
//        pyramid1.notifyObservers();
//
//        pyramid2.setAngels(70);
//        pyramid2.notifyObservers();
//
//        System.out.println(repository.getAllRecorders().get(0).getVolume());
//        System.out.println(repository.getAllRecorders().get(1).getVolume());
//    }
}
