package test.com.epam.objects.factory;

import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.InvalidDataAmountException;
import com.epam.objects.factory.PointFactoryImpl;
import com.epam.objects.factory.PyramidFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class created for check factory method.
 */
public class FactoryTest {

    /**
     * Data provider for create points list for pyramid.
     * @return arrays list of points.
     */
    @DataProvider(name = "list of points")
    public Object[][] createListOfPoints() {
        return
                new Object[][]{
                        {new ArrayList<Point>() {
                            {
                                add(new Point(1.0, 2.0, 3.0));
                                add(new Point(0.0, 0.0, 3.0));
                            }
                        }
                        }
                };
    }

    /**
     * {@inheritDoc}
     */
    @Test(description = "test on creating an object point")
    public void pointFactoryTest1() {
        Point expected = new Point(0.0, 0.0, 0.0);
        Point actual = new PointFactoryImpl().createPoint(0.0, 0.0, 0.0);
        Assert.assertEquals(actual, expected);
    }

    /**
     * {@inheritDoc}
     *
     * @throws InvalidDataAmountException when input data size in not correct.
     */
    @Test(description = "test on creating an object pyramid")
    public void pointFactoryTest2() throws InvalidDataAmountException {
        List<Point> points = new ArrayList<>() {
            {
                add(new Point(1.0, 2.0, 3.0));
                add(new Point(0.0, 0.0, 3.0));
            }
        };
        Pyramid expected = new Pyramid(points, 1.0, 3.0);
        Pyramid actual = new PyramidFactoryImpl()
                .createPyramid(points, 1.0, 3.0);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Checks when {@link Pyramid} constructor throws exception.
     * {@inheritDoc}
     *
     * @throws InvalidDataAmountException when input data size in not correct.
     */
    @Test(description = "test on creating an object pyramid, when size of list "
            + "is not correct",
            expectedExceptions = InvalidDataAmountException.class)
    public void pointFactoryTest3() throws InvalidDataAmountException {
        List<Point> points = new ArrayList<>() {
            {
                add(new Point(1.0, 2.0, 3.0));
            }
        };
        Pyramid expected = new Pyramid(points, 1.0, 2.0);
        Pyramid actual = new PyramidFactoryImpl()
                .createPyramid(points, 1.0, 2.0);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Checks when {@link Pyramid} setter throws exception.
     * {@inheritDoc}
     *
     * @throws InvalidDataAmountException when quantity of input data size in
     *                                    not correct.
     */
    @Test(description = "test on creating an object pyramid, when quantity of"
            + "angels is not correct",
            dataProvider = "list of points",
            expectedExceptions = InvalidDataAmountException.class)
    public void pointFactoryTest4(final List<Point> pointList)
            throws InvalidDataAmountException {
        Pyramid expected = new Pyramid(pointList, 10.0, 5.0);
        expected.setAngels(1);
        Pyramid actual = new PyramidFactoryImpl()
                .createPyramid(pointList, 10.0, 5.0);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Checks when {@link Pyramid} setter throws exception.
     * {@inheritDoc}
     *
     * @throws InvalidDataAmountException when value of input data size in not
     *                                    correct.
     */
    @Test(description = "test on creating an object pyramid, when value of"
            + "height is not correct",
            dataProvider = "list of points",
            expectedExceptions = InvalidDataAmountException.class)
    public void pointFactoryTest5(final List<Point> pointList)
            throws InvalidDataAmountException {
        Pyramid expected = new Pyramid(pointList, 10.0, 7.0);
        expected.setHeight(0);
        Pyramid actual = new PyramidFactoryImpl()
                .createPyramid(pointList, 10.0, 7.0);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Checks when {@link Pyramid} setter throws exception.
     * {@inheritDoc}
     *
     * @throws InvalidDataAmountException when z coordinates are not correct.
     */
    @Test(description = "test on creating an object pyramid, when points is"
            + "not correct",
            dataProvider = "list of points",
            expectedExceptions = InvalidDataAmountException.class)
    public void pointFactoryTest6(final List<Point> pointList)
            throws InvalidDataAmountException {
        Pyramid expected = new Pyramid(pointList, 10.0, 7.0);
        expected.setPoint(0.0, 6.0, 2.0, 1);
        Pyramid actual = new PyramidFactoryImpl()
                .createPyramid(pointList, 10.0, 7.0);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Checks when {@link Pyramid} setter throws exception.
     * {@inheritDoc}
     *
     * @throws InvalidDataAmountException when x, y coordinates are not correct.
     */
    @Test(description = "test on creating an object pyramid, when points is"
            + "not correct",
            dataProvider = "list of points",
            expectedExceptions = InvalidDataAmountException.class)
    public void pointFactoryTest7(final List<Point> pointList)
            throws InvalidDataAmountException {
        Pyramid expected = new Pyramid(pointList, 10.0, 7.0);
        expected.setPoint(1.0, 2.0, 3.0, 1);
        Pyramid actual = new PyramidFactoryImpl()
                .createPyramid(pointList, 10.0, 7.0);
        Assert.assertEquals(actual, expected);
    }
}
