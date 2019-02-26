package com.epam.objects.observer;

/**
 * Interface for class that observe for other class.
 */
public interface Observer {
    /**
     * Method calls when observable object changed state.
     * @param ob object that changed state.
     */
    void update(Object ob);
}
