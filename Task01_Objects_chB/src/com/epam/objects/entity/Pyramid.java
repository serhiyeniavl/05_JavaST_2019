package com.epam.objects.entity;

import com.epam.objects.exception.InvalidDataAmountException;
import com.epam.objects.observer.Observable;
import com.epam.objects.observer.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for restoring pyramid info.
 *
 * @author Vladislav Sergienya.
 */
public class Pyramid implements Geometry, Observable {
    /**
     * Logger for managing exception massages.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Pyramid.class);

    private List<Observer> observers;

    /**
     * List of pyramid basis points.
     */
    private List<Point> points;

    /**
     * Pyramid height.
     */
    private double height;

    /**
     * Number of pyramid basis angels.
     */
    private double angels;

    /**
     * Creates pyramid by input data.
     *
     * @param pointList  basis points.
     * @param h          height.
     * @param angelsQuan quantity of basis angels.
     * @throws InvalidDataAmountException when list size does not suite for
     * program requirements, when coordinates z did not arrange in one plane,
     * when coordinates x, y form one point.
     */
    public Pyramid(final List<Point> pointList, final double h,
                   final double angelsQuan) throws InvalidDataAmountException {
        if (pointList.size() != 2 || h <= 0.0 || angelsQuan <= 2
        || Double.compare(pointList.get(0).getZ(), pointList.get(1).getZ())
                != 0) {
            LOGGER.error("Invalid data when call constructor.");
            throw new InvalidDataAmountException("Incorrect data amount.");
        }
        this.points = pointList;
        this.height = h;
        this.angels = angelsQuan;
        observers = new ArrayList<>();
    }

    /**
     * Set pyramid basis point.
     *
     * @param coordinateX value x.
     * @param coordinateY value y.
     * @param coordinateZ value z.
     * @param index point index in list.
     * @throws InvalidDataAmountException when list size does not suite for
     * program requirements, when coordinates z did not arrange in one plane,
     * when coordinates x, y form one point.
     */
    public void setPoint(final double coordinateX, final double coordinateY,
                         final double coordinateZ, final int index)
            throws InvalidDataAmountException {
        if (index > 1) {
            LOGGER.error("Invalid data when try to set a point.");
            throw new InvalidDataAmountException("Invalid data.");
        }
        if ((index == 1
                && Double.compare(coordinateZ, points.get(0).getZ()) != 0)
                || (index == 0
                && Double.compare(coordinateZ, points.get(0).getZ()) != 0)) {
            LOGGER.error("Invalid plane. Plane must be parallel to"
                    + " coordinate plane.");
            throw new InvalidDataAmountException("Invalid coordinate z.");
        }
        if ((index == 0 && Double.compare(coordinateX, points.get(1).getX())
                == 0 && Double.compare(coordinateY, points.get(1).getY()) == 0)
                || (index == 1
                && Double.compare(coordinateX, points.get(0).getX()) == 0
                && Double.compare(coordinateY, points.get(0).getY()) == 0)) {
            LOGGER.error("Invalid points. They form one dot.");
            throw new InvalidDataAmountException("Invalid coordinates x, y.");
        }
        this.points.set(index, new Point(coordinateX, coordinateY,
                coordinateZ));
    }

    /**
     * @param h set pyramid height.
     * @throws InvalidDataAmountException when height is invalid.
     */
    public void setHeight(final double h) throws InvalidDataAmountException {
        if (h <= 0.0) {
            LOGGER.error("Set value is not correct.");
            throw new InvalidDataAmountException("Invalid amount.");
        }
        this.height = h;
    }

    /**
     * @param angelsQuan set angels quantity.
     * @throws InvalidDataAmountException when quantity of angels is invalid.
     */
    public void setAngels(final double angelsQuan)
            throws InvalidDataAmountException {
        if (angelsQuan <= 2.0) {
            LOGGER.error("Invalid amount of angels.");
            throw new InvalidDataAmountException("Invalid amount");
        }
        this.angels = angelsQuan;
    }

    /**
     * @param index point index.
     * @return pyramid point.
     */
    public Point getPoint(final int index) {
        return points.get(index);
    }

    /**
     * @return pyramid height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return number of angels.
     */
    public double getAngels() {
        return angels;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Compares two object on equals.
     * {@inheritDoc}
     *
     * @param o object to compare.
     * @return true when two com.epam.test.com.epam.objects are equal, false when are not.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pyramid pyramid = (Pyramid) o;

        if (Double.compare(pyramid.height, height) != 0) {
            return false;
        }
        if (Double.compare(pyramid.angels, angels) != 0) {
            return false;
        }
        return points.equals(pyramid.points);
    }

    /**
     * Generates individual number representation of the object.
     * {@inheritDoc}
     *
     * @return object hashcode.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        final int coeff1 = 31;
        final int coeff2 = 32;
        result = points.hashCode();
        temp = Double.doubleToLongBits(height);
        result = coeff1 * result + (int) (temp ^ (temp >>> coeff2));
        temp = Double.doubleToLongBits(angels);
        result = coeff1 * result + (int) (temp ^ (temp >>> coeff2));
        return result;
    }

    /**
     * @return string representation of this object.
     */
    @Override
    public String toString() {
        return "Pyramid{"
                + "points=" + points
                + ", height=" + height
                + ", angels=" + angels
                + '}';
    }
}
