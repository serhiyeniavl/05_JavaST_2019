package test.com.epam.info_handling.entity;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.parser.AbstractTextParser;
import com.epam.info_handling.parser.LexemeParser;
import com.epam.info_handling.parser.LexemeStructureParser;
import com.epam.info_handling.parser.ParagraphParser;
import com.epam.info_handling.parser.SentenceParser;
import com.epam.info_handling.parser.SymbolParser;
import com.epam.info_handling.parser.TextParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class for TextComponent creating.
 */
public class TextComponentImplTest {
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
     * Data provider for test text component.
     *
     * @return text.
     */
    @DataProvider(name = "provide data for text composite")
    public Object[][] createDataForComposite() {
        return new Object[][]{
                {"    It survived has.\n"
                        + "    It has.", "    It survived has. \n"
                        + "    It has. \n"
                        + "    "},
                {"    It is a (111^5|1&2<<(2|5>>2&71))|1200 established fact "
                        + "that a reader will be of a page when looking at "
                        + "its layout.\n"
                        + "    Bye.",
                        "    It is a 1274 established fact that a reader will "
                                + "be of a page when looking at its layout. "
                                + "\n    Bye. \n    "
                }
        };
    }

    /**
     * Tests text composite creating.
     *
     * @param data source text.
     * @param expected expected text when composite is created.
     */
    @Test(dataProvider = "provide data for text composite")
    public void testToString(final String data, final String expected) {
        textParser.parseText(data);
        TextComponent component = textParser.getParsedText();

        Assert.assertEquals(component.toString(), expected);
    }
}
