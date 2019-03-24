package test.com.epam.info_handling.reader;

import com.epam.info_handling.exception.FatalRuntimeException;
import com.epam.info_handling.reader.Reader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Class for test reading from file.
 */
public class ReaderTest {
    /**
     * Path to correct file.
     */
    private static final String TEST_FILE_PATH = "data" + File.separator
            + "test_file.txt";
    /**
     * Path to incorrect file.
     */
    private static final String INCORRECT_FILE_PATH = "data" + File.separator
            + "empty_file.txt";

    /**
     * Test when file path is correct.
     */
    @Test(description = "Testing data reading.")
    public void readDataTest() {
        Reader reader = new Reader();
        String expected =
                "    It is a (111^5|1&2<<(2|5>>2&71))|1200 established fact "
                        + "that a reader will be of a page when looking at its "
                        + "layout.\n"
                        + "    Bye.";
        String actual = reader.readData(TEST_FILE_PATH);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test when file path is invalid and program throws RuntimeException.
     */
    @Test(description = "Testing of invalid file.",
            expectedExceptions = FatalRuntimeException.class)
    public void readInvalidDataTest() {
        Reader reader = new Reader();
        reader.readData(INCORRECT_FILE_PATH);
    }
}
