package test.com.epam.info_handling.action;

import com.epam.info_handling.action.PolishNotationAnalyzer;
import com.epam.info_handling.interpretator.ByteExpression;
import com.epam.info_handling.interpretator.ByteExpressionCalculator;
import com.epam.info_handling.interpretator.PolishNotationParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test class for polish notation creator and interpretator.
 */
public class ByteExpressionCalculatorTest {

    /**
     * Data provider for byte expression calc.
     *
     * @return expressions and expected values.
     */
    @DataProvider(name = "provide expression for calculating")
    public Object[][] createDataForAnalyzer() {
        return new Object[][]{
                {"5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)", 5.0},
                {"(111^5|1&2<<(2|5>>2&71))|1200", 1274},
                {"(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78", 78},
                {"30>>3", 3},
                {"1<<4", 16},
                {"1<<1", 2},
                {"~256", -257},
                {"1&~7", 0},
                {"~6&9|(3&4)", 9},
                {"30>>>3", 3}
        };
    }

    /**
     * Method that checks positive script when expression calculated right.
     *
     * @param exp      expression to calc.
     * @param expected expected value.
     */
    @Test(dataProvider = "provide expression for calculating")
    public void testCalculating(final String exp, final double expected) {
        PolishNotationParser notationParser = new PolishNotationParser();
        PolishNotationAnalyzer notationAnalyzer = new PolishNotationAnalyzer();
        ByteExpressionCalculator expressionCalculator
                = new ByteExpressionCalculator();
        String polishNotation = notationAnalyzer.analyzeAndGet(exp);
        List<ByteExpression> expressionList
                = notationParser.parse(polishNotation);
        double actual = expressionCalculator.handleExpression(expressionList);
        Assert.assertEquals(actual, expected);
    }
}
