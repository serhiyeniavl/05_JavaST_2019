package test.com.epam.objects.repository;

import com.epam.objects.entity.Point;
import com.epam.objects.entity.Pyramid;
import com.epam.objects.exception.InvalidDataAmountException;
import com.epam.objects.exception.NullArgumentException;
import com.epam.objects.registrator.PyramidRecorder;
import com.epam.objects.repository.PyramidRepositorySingleton;
import com.epam.objects.repository.specification.PyramidSpecification;
import com.epam.objects.repository.specification.find.FindPyramidByFirstPointSpecification;
import com.epam.objects.repository.specification.find.FindPyramidByIDSpecification;
import com.epam.objects.repository.specification.find.FindPyramidBySquareSpecification;
import com.epam.objects.repository.specification.find.FindPyramidByVolumeSpecification;
import com.epam.objects.repository.specification.sort.SortByFirstXPointSpecification;
import com.epam.objects.repository.specification.sort.SortByIdSpecification;
import com.epam.objects.repository.specification.sort.SortBySquareSpecification;
import com.epam.objects.repository.specification.sort.SortByVolumeSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PyramidRepositorySingletonTest {
    private PyramidRepositorySingleton repository;
    private List<Point> points1;
    private List<Point> points2;
    private Pyramid pyramid1;
    private Pyramid pyramid2;

    @BeforeClass
    private void init() throws InvalidDataAmountException,
            NullArgumentException {
        repository = PyramidRepositorySingleton.getInstance();

        points1 = new ArrayList<>();
        points2 = new ArrayList<>();

        points1.add(new Point(3, 3, 1));
        points1.add(new Point(3, 5, 1));

        points2.add(new Point(5, 7, 1));
        points2.add(new Point(15, 9, 1));

        pyramid1 = new Pyramid(points1, 7, 4);
        pyramid2 = new Pyramid(points2, 10, 4);

        PyramidRecorder recorder1 = new PyramidRecorder();
        PyramidRecorder recorder2 = new PyramidRecorder();
        recorder1.register(pyramid1);
        recorder2.register(pyramid2);

        repository.save(pyramid2, recorder2);
        repository.save(pyramid1, recorder1);
    }

    @AfterClass
    private void clear() {
        repository.clearRepository();
    }


    @DataProvider(name = "create data for test update method")
    private Object[][] createData() {
        return
                new Object[][]{
                        {160.57789}
                };
    }

    @DataProvider(name = "create list for sorts")
    private Object[][] createList() {
        return
                new Object[][]{
                        {new ArrayList() {
                            {
                                add(pyramid1);
                                add(pyramid2);
                            }
                        }}
                };
    }

    @Test(description = "Test situation when object changes state.",
            dataProvider = "create data for test update method")
    public void testUpdate1(final double expected) throws
            InvalidDataAmountException, NullArgumentException {
        Pyramid pyramid = new Pyramid(points1, 4, 5);

        PyramidRecorder recorder1 = new PyramidRecorder();
        recorder1.register(pyramid1);

        repository.save(pyramid, recorder1);
        pyramid.addObserver(repository);
        pyramid.addObserver(recorder1);

        pyramid.setHeight(70);
        pyramid.notifyObservers();

        Assert.assertEquals(repository.getAllRecorders().get(2).getVolume(),
                expected, 0.01);
    }

    @Test(description = "Test situation when repository throws exception.",
            expectedExceptions = NullArgumentException.class)
    public void testUpdate2() throws
            NullArgumentException {
        repository.save(null, null);
        Assert.assertEquals(repository.getAllRecorders().get(0).getVolume(),
                1, 0.01);
    }

    @Test(description = "Test situation when repository throws exception.",
            expectedExceptions = NullArgumentException.class)
    public void testUpdate3() throws
            NullArgumentException {
        repository.delete(null);
        Assert.assertEquals(repository.getAllRecorders().get(0).getVolume(),
                1, 0.01);
    }

    @Test(description = "Test situation when repository throws exception.",
            expectedExceptions = InvalidDataAmountException.class)
    public void testUpdate4() throws
            InvalidDataAmountException {
        repository.delete(-1);
        Assert.assertEquals(repository.getAllRecorders().get(0).getVolume(),
                1, 0.01);
    }

    @Test(description = "Test query to repository sort by square",
            dataProvider = "create list for sorts")
    public void testQuery1(final List<Pyramid> expected) {

        PyramidSpecification specification
                = new SortBySquareSpecification();
        List<Pyramid> actual = repository.query(specification);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Test query to repository sort by volume",
            dataProvider = "create list for sorts")
    public void testQuery2(final List<Pyramid> expected) {

        PyramidSpecification specification
                = new SortByVolumeSpecification();
        List<Pyramid> actual = repository.query(specification);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Test query to repository sort by id",
            dataProvider = "create list for sorts")
    public void testQuery3(final List<Pyramid> expected) {
        PyramidSpecification specification
                = new SortByIdSpecification();
        List<Pyramid> actual = repository.query(specification);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Test query to repository sort by first x point",
            dataProvider = "create list for sorts")
    public void testQuery4(final List<Pyramid> expected) {
        PyramidSpecification specification
                = new SortByFirstXPointSpecification();
        List<Pyramid> actual = repository.query(specification);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Test query to repository sort by first y point",
            dataProvider = "create list for sorts")
    public void testQuery5(final List<Pyramid> expected) {
        PyramidSpecification specification
                = new SortByFirstXPointSpecification();
        List<Pyramid> actual = repository.query(specification);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Test query to repository find pyramid by id",
            dataProvider = "create list for sorts")
    public void testQuery6(final List<Pyramid> expected)
            throws InvalidDataAmountException {
        PyramidSpecification specification
                = new FindPyramidByIDSpecification(1);
        List<Pyramid> actual = repository.query(specification);
        Assert.assertEquals(actual.get(0), expected.get(1));
    }

    @Test(description = "Test query to repository find pyramid by square",
            dataProvider = "create list for sorts")
    public void testQuery7(final List<Pyramid> expected) {
        PyramidSpecification specification
                = new FindPyramidBySquareSpecification(1, 100);
        List<Pyramid> actual = repository.query(specification);
        Assert.assertEquals(actual.get(0), expected.get(0));
    }

    @Test(description = "Test query to repository find pyramid by volume",
            dataProvider = "create list for sorts")
    public void testQuery8(final List<Pyramid> expected) {
        PyramidSpecification specification
                = new FindPyramidByVolumeSpecification(300, 1000);
        List<Pyramid> actual = repository.query(specification);
        Assert.assertEquals(actual.get(0), expected.get(1));
    }

    @Test(description = "Test query to repository find pyramid by first point",
            dataProvider = "create list for sorts")
    public void testQuery9(final List<Pyramid> expected) throws
            NullArgumentException {
        PyramidSpecification specification
                = new FindPyramidByFirstPointSpecification(
                new Point(3, 3, 1)
        );
        List<Pyramid> actual = repository.query(specification);
        Assert.assertEquals(actual.get(0), expected.get(0));
    }
}
