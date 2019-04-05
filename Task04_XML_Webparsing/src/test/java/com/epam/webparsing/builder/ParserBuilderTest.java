package com.epam.webparsing.builder;

import com.epam.webparsing.entity.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for test xml parsers. Uses common provider for all parsers.
 * Result must be the same.
 */
public class ParserBuilderTest {
    /**
     * Path to test file 1.
     */
    private static final String PATH1 = "src"
            + File.separator + "main" + File.separator
            + "data" + File.separator + "test" + File.separator + "test1.xml";
    /**
     * Path to test file 2.
     */
    private static final String PATH2 = "src"
            + File.separator + "main" + File.separator
            + "data" + File.separator + "test" + File.separator + "test2.xml";
    /**
     * Path to test file 3.
     */
    private static final String PATH3 = "src"
            + File.separator + "main" + File.separator
            + "data" + File.separator + "test" + File.separator + "test3.xml";

    /**
     * List of candies form first test file.
     */
    private List<Candie> candies1;
    /**
     * List of candies form second test file.
     */
    private List<Candie> candies2;
    /**
     * List of candies form third test file.
     */
    private List<Candie> candies3;

    /**
     * Method is preparing data for provider.
     */
    @BeforeClass
    public void setUp() throws ParseException {
        candies1 = new ArrayList<>();
        candies2 = new ArrayList<>();
        candies3 = new ArrayList<>();

        Candie chocolateCandie1 = new ChocolateCandie();
        Candie chocolateCandie2 = new ChocolateCandie();

        chocolateCandie1.setProduction("Коммунарка");
        chocolateCandie1.setName("Столичные-элит");
        chocolateCandie1.setFilling("liquor");
        chocolateCandie1.setEnergy(405);
        chocolateCandie1.setDate(new SimpleDateFormat("yyyy-MM-dd")
                .parse("2019-03-06"));
        chocolateCandie1.setChocolateType(ChocolateType.ШОКОЛОДНАЯ_ГЛАЗУРЬ);
        chocolateCandie1.getValue().setProtein(3.4);
        chocolateCandie1.getValue().setCarbohydrates(69.5);
        chocolateCandie1.getValue().setFats(14.3);
        chocolateCandie1.getIngredients().setVanillin("5");
        chocolateCandie1.getIngredients().setFructose(9);
        chocolateCandie1.getIngredients().setSugar(12);
        chocolateCandie1.getIngredients().setWater(13);

        chocolateCandie2.setProduction("Коммунарка");
        chocolateCandie2.setName("Беловежская пуща");
        chocolateCandie2.setFilling("fruit mush");
        chocolateCandie2.setEnergy(422);
        chocolateCandie2.setDate(new SimpleDateFormat("yyyy-MM-dd")
                .parse("2019-02-08"));
        chocolateCandie2.setChocolateType(ChocolateType.ШОКОЛОДНАЯ_ГЛАЗУРЬ);
        chocolateCandie2.getValue().setProtein(1.7);
        chocolateCandie2.getValue().setCarbohydrates(71.0);
        chocolateCandie2.getValue().setFats(12.4);
        chocolateCandie2.getIngredients().setVanillin("6");
        chocolateCandie2.getIngredients().setFructose(7);
        chocolateCandie2.getIngredients().setSugar(13);
        chocolateCandie2.getIngredients().setWater(10);

        candies1.addAll(Arrays.asList(chocolateCandie1, chocolateCandie2));

        Candie chocolateCandie3 = new ChocolateCandie();
        Candie fruitCandie1 = new FruitCandie();

        chocolateCandie3.setProduction("Спартак");
        chocolateCandie3.setName("Аэрофлотские");
        chocolateCandie3.setFilling("Вафля");
        chocolateCandie3.setEnergy(543);
        chocolateCandie3.setDate(new SimpleDateFormat("yyyy-MM-dd")
                .parse("2019-03-14"));
        chocolateCandie3.setChocolateType(ChocolateType.КАКАО_ШОКОЛАД);
        chocolateCandie3.getValue().setProtein(3.9);
        chocolateCandie3.getValue().setCarbohydrates(58.0);
        chocolateCandie3.getValue().setFats(33.7);
        chocolateCandie3.getIngredients().setVanillin("6");
        chocolateCandie3.getIngredients().setFructose(13);
        chocolateCandie3.getIngredients().setSugar(6);
        chocolateCandie3.getIngredients().setWater(2);

        fruitCandie1.setProduction("Roshen");
        fruitCandie1.setName("Бешеные пчелки");
        fruitCandie1.setFilling("Желе");
        fruitCandie1.setEnergy(402);
        fruitCandie1.setDate(new SimpleDateFormat("yyyy-MM-dd")
                .parse("2019-03-12"));
        fruitCandie1.setFruitType(FruitType.ФРУКТОВОЕ_ЖЕЛЕ);
        fruitCandie1.getValue().setProtein(1.6);
        fruitCandie1.getValue().setCarbohydrates(79.0);
        fruitCandie1.getValue().setFats(15.0);
        fruitCandie1.getIngredients().setVanillin("2");
        fruitCandie1.getIngredients().setFructose(3);
        fruitCandie1.getIngredients().setSugar(6);
        fruitCandie1.getIngredients().setWater(2);

        candies2.addAll(Arrays.asList(chocolateCandie3, fruitCandie1));

        Candie chocolateCandie4 = new ChocolateCandie();

        chocolateCandie4.setProduction("Спартак");
        chocolateCandie4.setName("Вишенка");
        chocolateCandie4.setEnergy(423);
        chocolateCandie4.setDate(new SimpleDateFormat("yyyy-MM-dd")
                .parse("2019-03-09"));
        chocolateCandie4.setChocolateType(ChocolateType.КАКАО_ШОКОЛАД);
        chocolateCandie4.getValue().setProtein(4.4);
        chocolateCandie4.getValue().setCarbohydrates(51.4);
        chocolateCandie4.getValue().setFats(23.0);
        chocolateCandie4.getIngredients().setVanillin("3");
        chocolateCandie4.getIngredients().setFructose(12);
        chocolateCandie4.getIngredients().setSugar(5);
        chocolateCandie4.getIngredients().setWater(7);

        candies3.addAll(Arrays.asList(chocolateCandie4, fruitCandie1));
    }

    /**
     * Data provider for each parser. Contains ref to file and expected result.
     *
     * @return ref to file and expected result.
     */
    @DataProvider(name = "provider for parsers. Contains classes from xml.")
    public Object[][] createData() {
        return new Object[][]{
                {PATH1, candies1},
                {PATH2, candies2},
                {PATH3, candies3}
        };
    }

    /**
     * Method tests DOM model parser.
     *
     * @param path     path to file.
     * @param expected expected list.
     */
    @Test(dataProvider = "provider for parsers. Contains classes from xml.")
    public void testDOMBuildCandies(final String path,
                                    final List<Candie> expected) {
        ParserBuilder domBuilder = new DOMBuilder();
        Assert.assertEquals(Director.createCandies(domBuilder, path), expected);
    }

    /**
     * Method tests SAX model parser.
     *
     * @param path     path to file.
     * @param expected expected list.
     */
    @Test(dataProvider = "provider for parsers. Contains classes from xml.")
    public void testSAXBuildCandies(final String path,
                                    final List<Candie> expected) {
        ParserBuilder saxBuilder = new SAXBuilder();
        Assert.assertEquals(Director.createCandies(saxBuilder, path), expected);
    }

    /**
     * Method tests StAX model parser.
     *
     * @param path     path to file.
     * @param expected expected list.
     */
    @Test(dataProvider = "provider for parsers. Contains classes from xml.")
    public void testStAXBuildCandies(final String path,
                                     final List<Candie> expected) {
        ParserBuilder staxBuilder = new StAXBuilder();
        Assert.assertEquals(Director
                .createCandies(staxBuilder, path), expected);
    }
}
