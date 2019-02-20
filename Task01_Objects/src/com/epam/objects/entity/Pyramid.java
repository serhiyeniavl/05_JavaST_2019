package com.epam.objects.entity;

import java.util.List;

/**
 * Entity class for restoring pyramid info.
 * @author Vladislav Sergienya.
 */
public class Pyramid {
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
     * @param pointList basis points.
     * @param h height.
     * @param angelsQuan quantity of basis angels.
     */
    public Pyramid(final List<Point> pointList, final double h,
                   final double angelsQuan) {
        this.points = pointList;
        this.height = h;
        this.angels = angelsQuan;
    }

    /**
     * Set pyramid basis point.
     * @param coordinateX value x.
     * @param coordinateY value y.
     * @param coordinateZ value z.
     * @param index point index in list.
     */
    public void setPoint(final double coordinateX, final double coordinateY,
                         final double coordinateZ, final int index) {
        this.points.set(index, new Point(coordinateX, coordinateY,
                coordinateZ));
    }

    /**
     * @param h set pyramid height.
     */
    public void setHeight(final double h) {
        this.height = h;
    }

    /**
     * @param angelsQuan set angels quantity.
     */
    public void setAngels(final double angelsQuan) {
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

    /**
     * Compares two object on equals.
     * {@inheritDoc}
     * @param o object to compare.
     * @return true when two objects are equal, false when are not.
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
     * Generates individual representation of the object named hashcode.
     * {@inheritDoc}
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
