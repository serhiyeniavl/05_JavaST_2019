/*
 * Package has entity classes.
 */
package com.epam.objects.entity;


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
     * Point z.
     */
    private double z;

    /**
     * Creates object.
     *
     * @param coordinateX value x
     * @param coordinateY value y
     * @param coordinateZ value z
     */
    public Point(final double coordinateX, final double coordinateY,
                 final double coordinateZ) {
        this.x = coordinateX;
        this.y = coordinateY;
        this.z = coordinateZ;
    }

    /**
     * @return coordinate x
     */
    public double getX() {
        return x;
    }

    /**
     * @param coordinateX value to set point x.
     */
    public void setX(final double coordinateX) {
        this.x = coordinateX;
    }

    /**
     * @return coordinate y
     */
    public double getY() {
        return y;
    }

    /**
     * @param coordinateY value to set point y.
     */
    public void setY(final double coordinateY) {
        this.y = coordinateY;
    }

    /**
     * @return coordinate z.
     */
    public double getZ() {
        return z;
    }

    /**
     * @param coordinateZ value to set point z.
     */
    public void setZ(final double coordinateZ) {
        this.z = coordinateZ;
    }

    /**
     * Compares two objects.
     * @param o object for comparison,
     * @return true when this object equals of <code>Object</code> o.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) {
            return false;
        }
        if (Double.compare(point.y, y) != 0) {
            return false;
        }

        return Double.compare(point.z, z) == 0;
    }

    /**
     * Generates a hashcode of the object by it's fields.
     * @return Hashcode of the objects got in params.
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        final int coeff1 = 31;
        final int coeff2 = 32;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> coeff2));
        temp = Double.doubleToLongBits(y);
        result = coeff1 * result + (int) (temp ^ (temp >>> coeff2));
        temp = Double.doubleToLongBits(z);
        result = coeff1 * result + (int) (temp ^ (temp >>> coeff2));
        return result;
    }


    /**
     * String representation of this class.
     * @return string with data of this object.
     */
    @Override
    public String toString() {
        return "Point{"
                + "x=" + x
                + ", y=" + y
                + ", z=" + z
                + '}';
    }
}
