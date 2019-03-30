package com.epam.webparsing.builder;

import com.epam.webparsing.entity.Candie;

import java.util.List;

/**
 * Class for handling requests to parse document using specified parser.
 */
public final class Director {

    /**
     * Private constructor.
     */
    private Director() {
    }

    /**
     * Creates method of candies using specified parser.
     * @param parser parser for parse document.
     * @param path file path.
     * @return created list of candies.
     */
    public static List<Candie> createCandies(final ParserBuilder parser,
                                             final String path) {
        parser.buildCandies(path);
        return parser.getCandies();
    }
}
