package com.epam.webparsing.builder;

import com.epam.webparsing.entity.Candie;

import java.util.List;

public class Director {

    private Director() {
    }

    public static List<Candie> createCandies(final ParserBuilder parser,
                                             final String path) {
        parser.buildCandies(path);
        return parser.getCandies();
    }
}
