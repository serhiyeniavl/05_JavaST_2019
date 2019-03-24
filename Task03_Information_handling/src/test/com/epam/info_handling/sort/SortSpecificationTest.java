package test.com.epam.info_handling.sort;

import com.epam.info_handling.action.TextSorter;
import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.parser.AbstractTextParser;
import com.epam.info_handling.parser.LexemeParser;
import com.epam.info_handling.parser.LexemeStructureParser;
import com.epam.info_handling.parser.ParagraphParser;
import com.epam.info_handling.parser.SentenceParser;
import com.epam.info_handling.parser.SymbolParser;
import com.epam.info_handling.parser.TextParser;
import com.epam.info_handling.sort.LexemeSymbolSortSpecification;
import com.epam.info_handling.sort.ParagraphSortSpecification;
import com.epam.info_handling.sort.SortSpecification;
import com.epam.info_handling.sort.WordLengthSortSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test class for text components sort.
 */
public class SortSpecificationTest {
    /**
     * Text parser.
     */
    private AbstractTextParser textParser;

    /**
     * Initializing all parsers for parse the text.
     */
    @BeforeClass
    public void init() {
        textParser = new TextParser();
        AbstractTextParser paragraphParser = new ParagraphParser();
        AbstractTextParser sentenceParser = new SentenceParser();
        AbstractTextParser lexemeParser = new LexemeParser();
        AbstractTextParser lexemeStructureParser = new LexemeStructureParser();
        AbstractTextParser symbolParser = new SymbolParser();
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(lexemeStructureParser);
        lexemeStructureParser.setNext(symbolParser);
    }

    /**
     * Data provider for test text sort.
     *
     * @return test for sort.
     */
    @DataProvider(name = "provide data for paragraph sort")
    public Object[][] createDataForSort() {
        return new Object[][]{
                {"It. Has. Survived.\n"
                        + "    It. Has.\n"
                        + "    Bye.\n", "Bye. \n"
                        + "    It. Has. \n"
                        + "    It. Has. Survived. \n"
                        + "    "}
        };
    }

    /**
     * Data provider for test text sort.
     *
     * @return test for sort.
     */
    @DataProvider(name = "provide data for sentence sort")
    public Object[][] createDataForSort1() {
        return new Object[][]{
                {"    It survived has.\n"
                        + "    It has.", "It has. survived"
                        + " It has. "}
        };
    }

    /**
     * Data provider for test text sort.
     *
     * @return test for sort.
     */
    @DataProvider(name = "provide data for lexeme sort")
    public Object[][] createDataForSort2() {
        return new Object[][]{
                {"    It survived has.\n"
                        + "    It has.", "has. has. It It survived "}
        };
    }

    /**
     * Method tests test sort by paragraphs.
     *
     * @param data     text for sort.
     * @param expected expected text after sort.
     */
    @Test(dataProvider = "provide data for paragraph sort")
    public void paragraphTestSort(final String data, final String expected) {
        SortSpecification paragraphSort = new ParagraphSortSpecification();

        textParser.parseText(data);
        TextComponent parsedText = textParser.getParsedText();

        List<TextComponent> sortedComponents
                = new TextSorter().query(paragraphSort, parsedText);

        StringBuilder actual = new StringBuilder();
        for (TextComponent component : sortedComponents) {
            actual.append(component.toString());
        }
        Assert.assertEquals(actual.toString(), expected);
    }

    /**
     * Method tests test sort by lexeme length in each sentence.
     *
     * @param data     text for sort.
     * @param expected expected text after sort.
     */
    @Test(dataProvider = "provide data for sentence sort")
    public void sentenceTestSort(final String data, final String expected) {
        SortSpecification sentenceSort = new WordLengthSortSpecification();

        textParser.parseText(data);
        TextComponent parsedText = textParser.getParsedText();

        List<TextComponent> sortedComponents
                = new TextSorter().query(sentenceSort, parsedText);

        StringBuilder actual = new StringBuilder();
        for (TextComponent component : sortedComponents) {
            actual.append(component.toString());
        }
        Assert.assertEquals(actual.toString(), expected);
    }

    /**
     * Method tests test sort by lexeme length in each sentence.
     *
     * @param data     text for sort.
     * @param expected expected text after sort.
     */
    @Test(dataProvider = "provide data for lexeme sort")
    public void lexemeTestSort(final String data, final String expected) {
        SortSpecification sentenceSort = new LexemeSymbolSortSpecification();

        textParser.parseText(data);
        TextComponent parsedText = textParser.getParsedText();

        List<TextComponent> sortedComponents
                = new TextSorter().query(sentenceSort, parsedText, 'h');

        StringBuilder actual = new StringBuilder();
        for (TextComponent component : sortedComponents) {
            actual.append(component.toString());
        }
        Assert.assertEquals(actual.toString(), expected);
    }
}
