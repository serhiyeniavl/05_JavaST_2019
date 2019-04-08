package com.epam.webparsing.runner;

import com.epam.webparsing.builder.DOMBuilder;
import com.epam.webparsing.builder.Director;
import com.epam.webparsing.builder.ParserBuilder;
import com.epam.webparsing.builder.SAXBuilder;
import com.epam.webparsing.builder.StAXBuilder;
import com.epam.webparsing.entity.Candie;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladislav Sergienya.
 * @version 1.0
 */
public final class Runner {
    /**
     * Private constructor.
     */
    private Runner() {
    }

    /**
     * Method creates and shows list of candies using different xml parsers.
     * @param args console args.
     */
    public static void main(final String[] args) {
        ParserBuilder domBuilder = new DOMBuilder();
        ParserBuilder saxBuilder = new SAXBuilder();
        ParserBuilder staxBuilder = new StAXBuilder();

        List<Candie> domCandies = createCandiesList(domBuilder);
        List<Candie> saxCandies = createCandiesList(saxBuilder);
        List<Candie> staxCandies = createCandiesList(staxBuilder);

        print(domCandies);
        print(saxCandies);
        print(staxCandies);

//        List<Candie> anton = new ArrayList<>();
//        anton.stream().

    }

    /**
     * Creates list of candies.
     * @param builder builder to use when creates list.
     * @return list of candies.
     */
    private static List<Candie> createCandiesList(final ParserBuilder builder) {
        return Director.createCandies(builder, "src"
                + File.separator + "main" + File.separator
                + "data" + File.separator + "candies.xml");
    }

    /**
     * Print list on console.
     * @param candies list to print.
     */
    private static void print(final List<Candie> candies) {
        candies.forEach(candie -> System.out.println(candie + "\n"));
    }
}
