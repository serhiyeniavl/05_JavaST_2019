package test.com.epam.info_handling.reader;

import com.epam.info_handling.exception.FatalRuntimeException;
import com.epam.info_handling.reader.Reader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ReaderTest {
    private static final String TEST_FILE_PATH = "data" + File.separator
            + "test_file.txt";
    private static final String INCORRECT_FILE_PATH = "data" + File.separator
            + "empty_file.txt";

    @Test(description = "Testing data reading.")
    public void readDataTest() {
        Reader reader = new Reader();
        String expected = "    It is a (111^5|1&2<<(2|5>>2&71))|1200 "
                + "established fact that a reader will be of a\n"
                + "page when looking at its layout.\n"
                + "\tBye.";
        String actual = reader.readData(TEST_FILE_PATH);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Testing of invalid file.",
            expectedExceptions = FatalRuntimeException.class)
    public void readInvalidDataTest() {
        Reader reader = new Reader();
        reader.readData(INCORRECT_FILE_PATH);
    }
}
