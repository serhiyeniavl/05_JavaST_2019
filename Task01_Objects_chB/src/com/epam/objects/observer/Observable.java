package com.epam.objects.observer;

public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers();
    void removeObserver(Observer observer);
}
