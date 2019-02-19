package com.epam.objects.entity;

import java.util.List;

public class Pyramid {
    private List<Point> points;
    private double height;
    private double angels;

    public Pyramid(final List<Point> pointList, final double h,
                   final double angelsQuan) {
        this.points = pointList;
        this.height = h;
        this.angels = angelsQuan;
    }

    public void setPoint(final double coordinateX, final double coordinateY,
                         final double coordinateZ, final int index) {
        this.points.set(index, new Point(coordinateX, coordinateY,
                coordinateZ));
    }

    public void setHeight(final double h) {
        this.height = h;
    }

    public void setAngels(final double angelsQuan) {
        this.angels = angelsQuan;
    }

    public Point getPoint(final int index) {
        return points.get(index);
    }

    public double getHeight() {
        return height;
    }

    public double getAngels() {
        return angels;
    }

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

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = points.hashCode();
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(angels);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Pyramid{"
                + "points=" + points
                + ", height=" + height
                + ", angels=" + angels
                + '}';
    }
}
