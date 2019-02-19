package test.com.epam.objects.validator;

import com.epam.objects.validator.ObjectValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("CheckStyle")
public class ObjectValidatorTest {

    @DataProvider(name = "input_correct_data")
    public Object[][] createCorrectData() {
        return
                new Object[][] {
                        {new ArrayList<Double>(Arrays.asList(3.0, 1.0, 0.0,
                                3.0, 25.0, 0.0, 10.0, 4.0, 4.0))}
                };
    }

    @DataProvider(name = "input_incorrect_data")
    public Object[][] createIncorrectData() {
        return
                new Object[][] {
                        {new ArrayList<Double>(Arrays.asList(3.0, 1.0, 0.0,
                                3.0, 25.0, 3.0, 10.0, 4.0, 4.0))}
                };
    }

    @Test(description = "Positive script when input data is correct",
            dataProvider = "input_correct_data")
    public void testValidate1(List<Double> data) {
        boolean expected = true;
        boolean actual = new ObjectValidator(data).validate();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Negative script when input data is not correct",
            dataProvider = "input_incorrect_data")
    public void testValidate2(List<Double> data) {
        boolean expected = false;
        boolean actual = new ObjectValidator(data).validate();
        Assert.assertEquals(actual, expected);
    }
}