package com.epam.objects.registrator;

import com.epam.objects.action.Calculator;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.observer.Observer;

/**
 * Class stores pyramid characteristics.
 */
public class PyramidRecorder implements Observer {
    /**
     * Pyramid volume.
     */
    private double volume;

    /**
     * Pyramid square.
     */
    private double square;

    /**
     * Pyramid id for link recorder with pyramid.
     */
    private int id;

    /**
     * Initializing pyramid id and calc pyramids characteristic.
     * @param pyramid object of pyramid for register id.
     */
    public void register(final Pyramid pyramid) {
        id = pyramid.getId();
        calc(pyramid);
    }

    /**
     * @return recorder id.
     */
    public int getId() {
        return id;
    }

    /**
     * @return registered pyramid volume.
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Sets pyramid volume.
     * @param vol value for set.
     */
    public void setVolume(final double vol) {
        this.volume = vol;
    }

    /**
     * @return pyramid square.
     */
    public double getSquare() {
        return square;
    }

    /**
     * Sets pyramid square.
     * @param sq value for set.
     */
    public void setSquare(final double sq) {
        this.square = sq;
    }

    /**
     * Method inherited from {@link Observer} interface. Update pyramids
     * characteristic when it change own state.
     * @param ob object that changed state.
     */
    @Override
    public void update(final Object ob) {
        calc((Pyramid) ob);
    }

    /**
     * Calc pyramid characteristics.
     * @param pyramid object for calc.
     */
    private void calc(final Pyramid pyramid) {
        Calculator calculator = new Calculator();
        volume = calculator.calcVolume(pyramid);
        square = calculator.calcSquare(pyramid);
    }
}
