package com.epam.objects.repository;

import com.epam.objects.entity.Pyramid;
import com.epam.objects.observer.Observer;
import com.epam.objects.registrator.PyramidRecorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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

    public void save(final Pyramid pyramid) {
        //if ()
        pyramids.add(pyramid);
        PyramidRecorder recorder = new PyramidRecorder();
        recorder.register(pyramid);
        recorders.add(recorder);
    }

    public List<Pyramid> getAll() {
        return new ArrayList<>(pyramids);
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
}
