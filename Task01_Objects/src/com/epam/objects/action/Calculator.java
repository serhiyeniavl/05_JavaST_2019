package com.epam.objects.action;

import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;

public class Calculator {

    public double calcVolume(final Pyramid pyramid) {
        final double coeff = 1.0 / 3.0;
        return coeff * calcBasisSquare(pyramid) * pyramid.getHeight();
    }

    public double calcSquare(final Pyramid pyramid) {
        final double coeff = 1.0 / 2.0;
        return calcBasisSquare(pyramid) + (pyramid.getAngels()
                * coeff * calcBasisSide(pyramid.getPoint(0),
                pyramid.getPoint(1)) * calcApothem(pyramid));
    }

    public double calcRatioTruncatedPyramids(final Pyramid pyramid,
                                             final double littleHeight) {
        final double truncatedPyramidVolume = calcVolume(pyramid)
                - calcLittlePyramidVolume(calcLittleBasisSquare(pyramid,
                littleHeight), littleHeight);
        return truncatedPyramidVolume
                / calcLittlePyramidVolume(calcLittleBasisSquare(pyramid,
                littleHeight), littleHeight);
    }

    private double calcBasisSide(final Point point1, final Point point2) {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2)
                + Math.pow(point2.getY() - point1.getY(), 2));
    }

    private double calcBasisSquare(final Pyramid pyramid) {
        final double coeff4 = 4.0;
        final double pow2 = 2.0;
        return pyramid.getAngels() / coeff4 * Math.pow(calcBasisSide(
                pyramid.getPoint(0), pyramid.getPoint(1)), pow2)
                * (1.0 / Math.tan(Math.PI / pyramid.getAngels()));
    }

    private double calcApothem(final Pyramid pyramid) {
        return Math.sqrt(pyramid.getHeight() * pyramid.getHeight()
                + Math.pow(calcInsideRadius(calcBasisSide(pyramid
                .getPoint(0), pyramid.getPoint(1)), pyramid), 2));
    }

    private double calcInsideRadius(final double sideLen,
                                    final Pyramid pyramid) {
        final double piRad = 180.0;
        return sideLen
                / (2 * Math.tan(Math.toRadians(piRad / pyramid.getAngels())));
    }

    private double calcLittleBasisSquare(final Pyramid pyramid,
                                         final double littleHeight) {
        return littleHeight * littleHeight * calcBasisSquare(pyramid)
                / (pyramid.getHeight() * pyramid.getHeight());
    }

    private double calcLittlePyramidVolume(final double littleBasisSquare,
                                           final double littleHeight) {
        final double coeff = 1.0 / 3.0;
        return coeff * littleBasisSquare * littleHeight;
    }
}
