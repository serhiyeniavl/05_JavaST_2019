/*
 * Package has entity classes.
 */
package com.epam.objects.entity;

import java.util.Objects;

/**
 * Entity class for describe a point in two dimensional space.
 *
 * @author Vladislav Sergienya.
 */
public class Point {
    /**
     * Point x.
     */
    private double x;
    /**
     * Point y.
     */
    private double y;

    /**
     * Creates object.
     * @param coordinateX value x
     * @param coordinateY value y
     */
    public Point(final double coordinateX, final double coordinateY) {
        this.x = coordinateX;
        this.y = coordinateY;
    }

    /**
     * Return value x.
     * @return coordinate x
     */
    public double getX() {
        return x;
    }

    /**
     * Set a value x.
     * @param coordinateX value to set point x.
     */
    public void setX(final double coordinateX) {
        this.x = coordinateX;
    }

    /**
     * Return value y.
     * @return coordinate y
     */
    public double getY() {
        return y;
    }

    /**
     * Set a value y.
     * @param coordinateY value to set point y
     */
    public void setY(final double coordinateY) {
        this.y = coordinateY;
    }

    /**
     * Compares two objects.
     * @param o object for comparison,
     * @return true when this object equals of <code>Object</code> o.
     */
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0
                && Double.compare(point.y, y) == 0;
    }

    /**
     * Generates a hashcode of the object by it's fields.
     * @return Hashcode of the objects got in params.
     * @see Objects#hash(Object...)
     * {@inheritDoc}
     */
    public int hashCode() {
        int result;
        long temp;
        final int coeff1 = 31;
        final int coeff2 = 32;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> coeff2));
        temp = Double.doubleToLongBits(y);
        result = coeff1 * result + (int) (temp ^ (temp >>> coeff2));
        return result;
    }

    /**
     * String representation of this class.
     * @return string with data of this object.
     */
    public String toString() {
        return "Point{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }
}
