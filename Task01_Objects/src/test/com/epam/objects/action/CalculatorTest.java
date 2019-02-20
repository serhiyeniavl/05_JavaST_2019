package test.com.epam.objects.action;

import com.epam.objects.action.Calculator;
import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CheckStyle")
public class CalculatorTest {

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

    @DataProvider(name = "square_calc_pyramid")
    public Object[][] createDataSquare() {
        return
                new Object[][] {
                        {new ArrayList<Point>() {
                            {
                                add(new Point(3.0, 1.0, 0.0));
                                add(new Point(3.0, 25.0, 0.0));
                            }
                        }, 10.0, 4, 1325.7839}
                };
    }

    @DataProvider(name = "volume_ratio_calc_pyramid")
    public Object[][] createDataVolRatio() {
        return
                new Object[][] {
                        {new ArrayList<Point>() {
                            {
                                add(new Point(3.0, 1.0, 0.0));
                                add(new Point(3.0, 25.0, 0.0));
                            }
                        }, 10.0, 4, 14.625}
                };
    }

    @Test(description = "Check volume calculator",
            dataProvider = "volume_calc_pyramid")
    public void testCalcVolume(List<Point> points, double height, double angels,
                               double expected) {
        double actual = new Calculator().calcVolume(new Pyramid(points, height, angels));
        Assert.assertEquals(actual, expected, 0.01);
    }

    @Test(description = "Check square calculator",
          dataProvider = "square_calc_pyramid")
    public void testCalcSquare(List<Point> points, double height, double angels,
                               double expected) {
        double actual = new Calculator().calcSquare(new Pyramid(points, height, angels));
        Assert.assertEquals(actual, expected, 0.01);
    }

    @Test(description = "Check volume ratio",
         dataProvider = "volume_ratio_calc_pyramid")
    public void testCalcVolumeRatio(List<Point> points, double height, double angels,
                               double expected) {
        double actual = new Calculator().calcRatioTruncatedPyramids(new Pyramid(points, height, angels), 4.0);
        Assert.assertEquals(actual, expected, 0.01);
    }
}