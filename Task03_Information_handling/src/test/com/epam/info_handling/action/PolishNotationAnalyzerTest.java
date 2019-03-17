package test.com.epam.info_handling.action;

import com.epam.info_handling.action.PolishNotationAnalyzer;
import com.epam.info_handling.interpretator.ByteExpression;
import com.epam.info_handling.interpretator.ByteExpressionCalculator;
import com.epam.info_handling.interpretator.PolishNotationParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class PolishNotationAnalyzerTest {
    private PolishNotationAnalyzer notationAnalyzer;
    private PolishNotationParser notationParser;
    private ByteExpressionCalculator expressionCalculator;

    @BeforeClass
    public void init() {
        notationParser = new PolishNotationParser();
        notationAnalyzer = new PolishNotationAnalyzer();
        expressionCalculator = new ByteExpressionCalculator();
    }

    /**
     * Data provider for volume calculating.
     *
     * @return expressions and expected values.
     */
    @DataProvider(name = "provide expression for analyzer")
    public Object[][] createDataForAnalyzer() {
        return new Object[][]{
                //{"5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)", 5.0},
                {"(111^5|1&2<<(2|5>>2&71))|1200", 1274},
                //{"(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78", 78},
                //{"30>>3", 3},
                //{"1<<4", 16}
        };
    }

    @Test(dataProvider = "provide expression for analyzer")
    public void testAnalyzeAndGet(final String exp, final double expected) {
        String polishNotation = notationAnalyzer.analyzeAndGet(exp);
        List<ByteExpression> expressionList = notationParser.parse(polishNotation);
        double actual = expressionCalculator.handleExpression(expressionList);
        Assert.assertEquals(actual, expected);
    }
}