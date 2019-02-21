package test.com.epam.objects.reader;

import com.epam.objects.exception.MissingFilePathException;
import com.epam.objects.reader.Reader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class test for {@link Reader}.
 */
public class ReaderTest {

    /**
     * Method tests {@link Reader} when path to file is null.
     * @throws MissingFilePathException when file path null.
     */
    @Test(description = "Negative script when file path is null",
            expectedExceptions = MissingFilePathException.class)
    public void getStringListTest1() throws MissingFilePathException {
        List<String> expected
                = new ArrayList<>(Arrays.asList("1", "2", "3"));
        List<String> actual = new Reader(null).getStringList();
        Assert.assertEquals(actual, expected);
    }

    /**
     * Method tests {@link Reader} when file path is correct.
     * @throws MissingFilePathException when path fo file is null.
     */
    @Test(description = "Positive script when file path is correct")
    public void getStringListTest2() throws MissingFilePathException {
        List<String> expected
                = new ArrayList<>(Arrays.asList("1", "2", "3"));
        List<String> actual = new Reader("data"
                + File.separator + "test1.txt").getStringList();
        Assert.assertEquals(actual, expected);
    }
}
