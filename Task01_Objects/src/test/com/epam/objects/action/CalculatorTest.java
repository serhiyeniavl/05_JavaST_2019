package test.com.epam.objects.action;

import com.epam.objects.action.Calculator;
import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.InvalidDataAmountException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for action class {@link Calculator}.
 */
public class CalculatorTest {

    /**
     * Data provider for volume calculating.
     * @return object of {@link Pyramid}
     */
    @DataProvider(name = "volume_calc_pyramid")
    public Object[][] createDataVolume() {
        return
                new Object[][]{
                        {new ArrayList<Point>() {
                            {
                                add(new Point(3.0, 1.0, 0.0));
                                add(new Point(3.0, 25.0, 0.0));
                            }
                        }, 10.0, 4, 1920}
                };
    }

    /**
     * Data provider for calculating square.
     * @return object of {@link Pyramid}
     */
    @DataProvider(name = "square_calc_pyramid")
    public Object[][] createDataSquare() {
        return
                new Object[][]{
                        {new ArrayList<Point>() {
                            {
                                add(new Point(3.0, 1.0, 0.0));
                                add(new Point(3.0, 25.0, 0.0));
                            }
                        }, 10.0, 4, 1325.7839}
                };
    }

    /**
     * Data provider for calculating volume ratio of two pyramids.
     * @return object of {@link Pyramid}
     */
    @DataProvider(name = "volume_ratio_calc_pyramid")
    public Object[][] createDataVolRatio() {
        return
                new Object[][]{
                        {new ArrayList<Point>() {
                            {
                                add(new Point(3.0, 1.0, 0.0));
                                add(new Point(3.0, 25.0, 0.0));
                            }
                        }, 10.0, 4, 14.625}
                };
    }

    /**
     * Test calculating volume calculating test.
     * {@inheritDoc}
     * @param points set pyramid points.
     * @param height set pyramid height.
     * @param angels set pyramid angels.
     * @param expected value form test.
     * @throws InvalidDataAmountException
     */
    @Test(description = "Check volume calculator",
            dataProvider = "volume_calc_pyramid")
    public void testCalcVolume(final List<Point> points, final double height,
                               final double angels, final double expected)
            throws InvalidDataAmountException {
        final double epsilon = 0.01;
        double actual = new Calculator().calcVolume(new Pyramid(points,
                height, angels));
        Assert.assertEquals(actual, expected, epsilon);
    }

    /**
     * Test checks square calculating method.
     * {@inheritDoc}
     * @param points set pyramid points.
     * @param height set pyramid height.
     * @param angels set pyramid angels.
     * @param expected value form test.
     * @throws InvalidDataAmountException
     */
    @Test(description = "Check square calculator",
            dataProvider = "square_calc_pyramid")
    public void testCalcSquare(final List<Point> points, final double height,
                               final double angels, final double expected)
            throws InvalidDataAmountException {
        final double epsilon = 0.01;
        double actual = new Calculator().calcSquare(new Pyramid(points,
                height, angels));
        Assert.assertEquals(actual, expected, epsilon);
    }

    /**
     * Test checks volume ratio calculating method.
     * {@inheritDoc}
     * @param points set pyramid points.
     * @param height set pyramid height.
     * @param angels set pyramid angels.
     * @param expected value form test.
     * @throws InvalidDataAmountException
     */
    @Test(description = "Check volume ratio",
            dataProvider = "volume_ratio_calc_pyramid")
    public void testCalcVolumeRatio(final List<Point> points,
                                    final double height, final double angels,
                                    final double expected)
            throws InvalidDataAmountException {
        final double epsilon = 0.01;
        final double littleHeight = 4.0;
        double actual = new Calculator()
                .calcRatioTruncatedPyramids(new Pyramid(points,
                        height, angels), littleHeight);
        Assert.assertEquals(actual, expected, epsilon);
    }
}
