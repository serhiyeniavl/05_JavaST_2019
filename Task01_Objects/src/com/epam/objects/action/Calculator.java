package com.epam.objects.action;

import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;

/**
 * Calculating square and volume of right pyramid.
 */
public class Calculator {
    /**
     * Counts pyramid volume.
     *
     * @param pyramid for counting.
     * @return volume of the pyramid.
     */
    public double calcVolume(final Pyramid pyramid) {
        final double coeff = 1.0 / 3.0;
        return coeff * calcBasisSquare(pyramid) * pyramid.getHeight();
    }

    /**
     * Counts pyramid square.
     *
     * @param pyramid for counting.
     * @return square of the pyramid.
     */
    public double calcSquare(final Pyramid pyramid) {
        final double coeff = 1.0 / 2.0;
        return calcBasisSquare(pyramid) + (pyramid.getAngels()
                * coeff * calcBasisSide(pyramid.getPoint(0),
                pyramid.getPoint(1)) * calcApothem(pyramid));
    }

    /**
     * Counts volume ratio truncated pyramid.
     *
     * @param pyramid      all pyramid.
     * @param littleHeight is height up pyramid
     * @return volume ratio.
     */
    public double calcRatioTruncatedPyramids(final Pyramid pyramid,
                                             final double littleHeight) {
        final double truncatedPyramidVolume = calcVolume(pyramid)
                - calcLittlePyramidVolume(calcLittleBasisSquare(pyramid,
                littleHeight), littleHeight);
        return truncatedPyramidVolume
                / calcLittlePyramidVolume(calcLittleBasisSquare(pyramid,
                littleHeight), littleHeight);
    }

    /**
     * Counts basis side len for use it in public methods.
     *
     * @param point1 first point of the side.
     * @param point2 second point of the side.
     * @return side length.
     */
    private double calcBasisSide(final Point point1, final Point point2) {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2)
                + Math.pow(point2.getY() - point1.getY(), 2));
    }

    /**
     * Counts basis square for use it in others formulas.
     * @param pyramid counting pyramid.
     * @return basis square.
     */
    private double calcBasisSquare(final Pyramid pyramid) {
        final double coeff4 = 4.0;
        final double pow2 = 2.0;
        return pyramid.getAngels() / coeff4 * Math.pow(calcBasisSide(
                pyramid.getPoint(0), pyramid.getPoint(1)), pow2)
                * (1.0 / Math.tan(Math.PI / pyramid.getAngels()));
    }

    /**
     * Counts pyramid apothem.
     * @param pyramid for count.
     * @return apothem length.
     */
    private double calcApothem(final Pyramid pyramid) {
        return Math.sqrt(pyramid.getHeight() * pyramid.getHeight()
                + Math.pow(calcInsideRadius(calcBasisSide(pyramid
                .getPoint(0), pyramid.getPoint(1)), pyramid), 2));
    }

    /**
     * Counts inside radius of the basis pyramid.
     * @param sideLen basis side length.
     * @param pyramid for count.
     * @return radius length.
     */
    private double calcInsideRadius(final double sideLen,
                                    final Pyramid pyramid) {
        final double piRad = 180.0;
        return sideLen
                / (2 * Math.tan(Math.toRadians(piRad / pyramid.getAngels())));
    }

    /**
     * Counts top square of the truncated pyramid.
     * @param pyramid for count.
     * @param littleHeight is height of the top pyramid.
     * @return top basis square.
     */
    private double calcLittleBasisSquare(final Pyramid pyramid,
                                         final double littleHeight) {
        return littleHeight * littleHeight * calcBasisSquare(pyramid)
                / (pyramid.getHeight() * pyramid.getHeight());
    }

    /**
     * Counts top pyramid volume.
     * @param littleBasisSquare top pyramid basis square.
     * @param littleHeight top pyramid height.
     * @return volume.
     */
    private double calcLittlePyramidVolume(final double littleBasisSquare,
                                           final double littleHeight) {
        final double coeff = 1.0 / 3.0;
        return coeff * littleBasisSquare * littleHeight;
    }
}
