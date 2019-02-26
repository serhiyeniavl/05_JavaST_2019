package com.epam.objects.observer;

/**
 * Interface for classes are observable.
 */
public interface Observable {
    /**
     * Method add new observer to object.
     * @param observer object to add.
     */
    void addObserver(Observer observer);

    /**
     * Method notify all observers that object changed state.
     */
    void notifyObservers();

    /**
     * Method removes observer.
     * @param observer object to remove.
     */
    void removeObserver(Observer observer);
}
