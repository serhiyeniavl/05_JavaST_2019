package com.epam.threads.validator;

import java.util.List;

/**
 * Class validating data form input file.
 */
public class FigureValidator {
    /**
     * Pattern for check figure.
     */
    private static final String FIGURE_PATTERN = "\\d+";

    /**
     * Pattern for check spaces between figures and letters.
     */
    private static final String STRING_SPLIT_PATTERN = "\\s+";

    /**
     * Private constructor, to not allow to create object of this class.
     */
    private FigureValidator() {
    }

    /**
     * Method validating file data. Data is not correct if size not suit or
     * symbol is not figure.
     * @param fileData data to check valid.
     * @return {@code true} if data is valid.
     */
    public static boolean validate(final List<String> fileData) {
        final int inputDataLen = 3;
        if (fileData.size() > 1) {
            return false;
        }
        for (String string : fileData) {
            if (string.split(STRING_SPLIT_PATTERN).length == inputDataLen) {
                for (String substring : string.split(STRING_SPLIT_PATTERN)) {
                    if (!substring.matches(FIGURE_PATTERN)) {
                        return false;
                    }
                }
            }
            if (string.split(STRING_SPLIT_PATTERN).length != inputDataLen) {
                return false;
            }
        }
        return true;
    }
}
