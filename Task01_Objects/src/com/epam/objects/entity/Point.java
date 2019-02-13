/**
 * Package has entity classes.
 */
package com.epam.objects.entity;

import java.util.Objects;

/**
 * Entity class for describe a point in three dimensional space.
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
     * Point z.
     */
    private double z;

    /**
     * Creates object {@link Point}
     * 
     * @param x value x
     * @param y value y
     * @param z value z
     */
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
     * @param x value to set point x.
     */
    public void setX(final double x) {
        this.x = x;
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
     * @param y value to set point y
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Return value z.
     * @return coordinate z
     */
    public double getZ() {
        return z;
    }

    /**
     * Set a value z.
     * @param z value to set a point z
     */
    public void setZ(final double z) {
        this.z = z;
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
                && Double.compare(point.y, y) == 0
                && Double.compare(point.z, z) == 0;
    }

    /**
     * Generates a hashcode of the object by it's fields.
     * @return Hashcode of the objects got in params.
     * @see Objects#hash(Object...)
     */
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    /**
     * String representation of this class.
     * @return string with data of this object.
     */
    public String toString() {
        return "Point{"
                + "x=" + x
                + ", y=" + y
                + ", z=" + z
                + '}';
    }
}
