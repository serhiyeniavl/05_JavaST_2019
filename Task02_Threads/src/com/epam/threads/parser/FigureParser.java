package com.epam.threads.parser;

import com.epam.threads.validator.FigureValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class use for parsing integer numbers from {@link List} of {@link String}.
 */
public class FigureParser {
    /**
     * Pattern for check spaces between figures and letters.
     */
    private static final String STRING_SPLIT_PATTERN = "\\s+";

    /**
     * Parsing integer numbers form {@link List}.
     *
     * @param stringList list for parsing.
     * @return {@link List} of parsed integers.
     */
    public List<Integer> parseInteger(final List<String> stringList) {
        List<Integer> figureList = new ArrayList<>();
        if (FigureValidator.validate(stringList)) {
            figureList = Stream.of(
                    stringList.get(0).split(STRING_SPLIT_PATTERN))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        return figureList;
    }
}
