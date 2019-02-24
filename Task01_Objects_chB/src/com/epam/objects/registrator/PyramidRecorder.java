package com.epam.objects.registrator;

import com.epam.objects.action.Calculator;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.observer.Observer;

public class PyramidRecorder implements Observer {
    private double volume;
    private double square;
    private int id;

    public void register(final Pyramid pyramid) {
        id = pyramid.getId();
        calc(pyramid);
    }

    public int getId() {
        return id;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(final double newVolume) {
        this.volume = newVolume;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double newSquare) {
        this.square = newSquare;
    }

    @Override
    public void update(Object ob) {
        calc((Pyramid)ob);
    }

    private void calc(final Pyramid pyramid) {
        Calculator calculator = new Calculator();
        volume = calculator.calcVolume(pyramid);
        square = calculator.calcSquare(pyramid);
    }
}
