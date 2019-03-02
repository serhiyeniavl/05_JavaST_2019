package com.epam.threads.validator;

import java.util.List;

public class FigureValidator {
    /**
     * Pattern for check figure.
     */
    private static final String FIGURE_PATTERN = "\\d+";

    /**
     * Pattern for check spaces between figures and letters.
     */
    private static final String STRING_SPLIT_PATTERN = "\\s+";

    private FigureValidator() {

    }

    public static boolean validate(final List<String> fileData) {
        if (fileData.size() > 1) {
            return false;
        }
        for (String string : fileData) {
            if (string.split(STRING_SPLIT_PATTERN).length == 3) {
                for (String substring : string.split(STRING_SPLIT_PATTERN)) {
                    if (!substring.matches(FIGURE_PATTERN)) {
                        return false;
                    }
                }
            }
            if (string.split(STRING_SPLIT_PATTERN).length != 3) {
                return false;
            }
        }
        return true;
    }
}
