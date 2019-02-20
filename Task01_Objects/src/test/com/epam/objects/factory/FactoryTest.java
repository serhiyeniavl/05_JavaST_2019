package test.com.epam.objects.factory;

import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.factory.PointFactoryImpl;
import com.epam.objects.factory.PyramidFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CheckStyle")
public class FactoryTest {

    @Test(description = "test on creating an object point")
    public void pointFactoryTest1() {
        Point expected = new Point(0.0, 0.0, 0.0);
        Point actual = new PointFactoryImpl().createPoint(0.0, 0.0, 0.0);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "test on creating an object pyramid")
    public void pointFactoryTest2() {
        List<Point> points = new ArrayList<>() {
            {
                add(new Point(1.0, 2.0, 3.0));
                add(new Point(0.0, 0.0, 0.0));
            }
        };
        Pyramid expected = new Pyramid(points, 1.0, 2.0);
        Pyramid actual = new PyramidFactoryImpl().createPyramid(points, 1.0, 2.0);
        Assert.assertEquals(actual, expected);
    }
}
