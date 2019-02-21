package test.com.epam.objects.parser;

import com.epam.objects.parser.FigureParser;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class checks figure parsing.
 */
public class FigureParserTest {
    /**
     * Object of {@link FigureParser} class.
     */
    private FigureParser figureParser;

    /**
     * Map that must be received after test.
     */
    private Map<Integer, List<Double>> expectedMap;

    /**
     * Method runs before test. Creates {@code expectedMap}
     */
    @BeforeTest
    private void init() {
        figureParser = new FigureParser();
        List<Double> doublesList = new ArrayList<>(Arrays.asList(1.0, 2.0,
                3.0, 4.0));
        expectedMap = new HashMap<>();
        expectedMap.put(0, doublesList);
    }

    /**
     * Check pasting double form file data.
     */
    @Test
    public void parseDoubleTest() {
        Map<Integer, List<Double>> expected = expectedMap;
        Map<Integer, List<Double>> actual = figureParser.parseDouble(
                Collections.singletonList("1 2 3 4"));
        Assert.assertEquals(actual, expected);
    }
}
