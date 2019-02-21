package test.com.epam.objects.validator;

import com.epam.objects.validator.ObjectValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class test for {@link ObjectValidator}.
 */
public class ObjectValidatorTest {

    /**
     * Data provider for validate correct data.
     * @return double list of data.
     */
    @DataProvider(name = "input_correct_data")
    public Object[][] createCorrectData() {
        return
                new Object[][] {
                        {new ArrayList<>(Arrays.asList(3.0, 1.0, 0.0,
                                3.0, 25.0, 0.0, 10.0, 4.0, 4.0)), true}
                };
    }

    /**
     * Data provider for validate invalid data.
     * @return double list of data.
     */
    @DataProvider(name = "input_incorrect_data")
    public Object[][] createIncorrectData1() {
        return
                new Object[][] {
                        {new ArrayList<>(Arrays.asList(3.0, 1.0, 0.0,
                                3.0, 25.0, 3.0, 10.0, 4.0, 4.0, 5.9)), false}
                };
    }

    /**
     * Data provider for validate invalid data.
     * @return double list of data.
     */
    @DataProvider(name = "input_incorrect_data_dot")
    public Object[][] createIncorrectData2() {
        return
                new Object[][] {
                        {new ArrayList<>(Arrays.asList(3.0, 1.0, 0.0,
                                3.0, 1.0, 0.0, 10.0, 4.0, 4.0)), false}
                };
    }

    /**
     * Data provider for validate invalid data.
     * @return double list of data.
     */
    @DataProvider(name = "input_incorrect_data_plane_not_parallel")
    public Object[][] createIncorrectData3() {
        return
                new Object[][] {
                        {new ArrayList<>(Arrays.asList(3.0, 3.0, 0.0,
                                3.0, 1.0, 2.0, 10.0, 4.0, 4.0)), false}
                };
    }

    /**
     * Data provider for validate invalid data.
     * @return double list of data.
     */
    @DataProvider(name = "input_incorrect_data_invalid_height")
    public Object[][] createIncorrectData4() {
        return
                new Object[][] {
                        {new ArrayList<>(Arrays.asList(3.0, 3.0, 0.0,
                                3.0, 1.0, 0.0, -10.0, 4.0, 4.0)), false}
                };
    }

    /**
     * Data provider for validate invalid data.
     * @return double list of data.
     */
    @DataProvider(name = "input_incorrect_data_angels")
    public Object[][] createIncorrectData5() {
        return
                new Object[][] {
                        {new ArrayList<>(Arrays.asList(3.0, 3.0, 0.0,
                                3.0, 1.0, 0.0, 10.0, 4.0, 1.0)), false}
                };
    }

    /**
     * Test method checks validate data when data is correct.
     * @param data for validate.
     * @param expected value from test.
     */
    @Test(description = "Positive script when input data is correct",
            dataProvider = "input_correct_data")
    public void testValidate1(final List<Double> data, final boolean expected) {
        boolean actual = new ObjectValidator(data).validate();
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test method checks validate data when data is invalid.
     * @param data for validate.
     * @param expected value from test.
     */
    @Test(description = "Negative script when input data is not correct",
            dataProvider = "input_incorrect_data")
    public void testValidate2(final List<Double> data, final boolean expected) {
        boolean actual = new ObjectValidator(data).validate();
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test method checks validate data when points are dot.
     * @param data for validate.
     * @param expected value from test.
     */
    @Test(description = "Negative script when input data is not correct",
            dataProvider = "input_incorrect_data_dot")
    public void testValidate3(final List<Double> data, final boolean expected) {
        boolean actual = new ObjectValidator(data).validate();
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test method checks validate data when plane not parallel x,y plane.
     * @param data for validate.
     * @param expected value from test.
     */
    @Test(description = "Negative script when input data is not correct",
            dataProvider = "input_incorrect_data_plane_not_parallel")
    public void testValidate4(final List<Double> data, final boolean expected) {
        boolean actual = new ObjectValidator(data).validate();
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test method checks validate data when height invalid.
     * @param data for validate.
     * @param expected value from test.
     */
    @Test(description = "Negative script when input data is not correct",
            dataProvider = "input_incorrect_data_invalid_height")
    public void testValidate5(final List<Double> data, final boolean expected) {
        boolean actual = new ObjectValidator(data).validate();
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test method checks validate data when angels value is not correct.
     * @param data for validate.
     * @param expected value from test.
     */
    @Test(description = "Negative script when input data is not correct",
            dataProvider = "input_incorrect_data_angels")
    public void testValidate6(final List<Double> data, final boolean expected) {
        boolean actual = new ObjectValidator(data).validate();
        Assert.assertEquals(actual, expected);
    }
}
