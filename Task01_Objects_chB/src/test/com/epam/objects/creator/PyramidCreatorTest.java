package test.com.epam.objects.creator;

import com.epam.objects.creator.PyramidCreator;
import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.InvalidDataAmountException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class for {@link PyramidCreator}.
 */
public class PyramidCreatorTest {

    /**
     * Tests {@link PyramidCreator} method for creating object. Const variables
     * taken from source file.
     * @throws InvalidDataAmountException when {@link Pyramid} get invalid data.
     */
    @Test
    public void testCreate() throws InvalidDataAmountException {
        List<Pyramid> expected = new ArrayList<>();
        final double firstXval = 3;
        final double firstYval = 2;
        final double firstZval = 1;
        final double secondXval = 4;
        final double secondYval = 5;
        final double secondZval = 1;
        final double height = 10;
        final double angels = 4;
        Point point1 = new Point(firstXval, firstYval, firstZval);
        Point point2 = new Point(secondXval, secondYval, secondZval);
        List<Point> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);
        expected.add(new Pyramid(points, height, angels));
        Map<Integer, List<Double>> data = new HashMap<>();
        data.put(0, Arrays.asList(firstXval, firstYval, firstZval,
                secondXval, secondYval, secondZval, height, 7.0, angels));
        List<Pyramid> actual;
        data.put(1, Arrays.asList(firstXval, firstYval, firstZval,
                secondXval, secondYval, secondZval, height, 70.0, angels));
        actual = new PyramidCreator().create(data);
        Assert.assertEquals(actual, expected);
    }
}
