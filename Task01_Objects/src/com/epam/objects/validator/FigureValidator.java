package com.epam.objects.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class checks figures in string. Using regex for that.
 * @author Vladislav Sergienya.
 */
public class FigureValidator {
    /**
     * Pattern for check figure.
     */
    private static final String FIGURE_PATTERN = "-?\\d+([.]\\d*)?";

    /**
     * Pattern for check spaces between figures and letters.
     */
    private static final String STRING_SPLIT_PATTERN = "\\s+";

    /**
     * Logger for logging info when string is not correct.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FigureValidator.class);

    /**
     * Validates string on figures on it. Skip the string if it contains of
     * incorrect symbols.
     * @param stringList string for validate.
     * @return {@link List} of figures.
     */
    public List<String> validate(final List<String> stringList) {
        List<String> figureList = new ArrayList<>();
        boolean isCorrect = true;

        for (String string : stringList) {
            for (String figure : string.split(STRING_SPLIT_PATTERN)) {
                if (!figure.matches(FIGURE_PATTERN)) {
                    LOGGER.info("Figure validation error: incorrect string "
                            + figure + ".");
                    isCorrect = false;
                    break;
                }
            }
            if (isCorrect) {
                figureList.add(string);
            }
            isCorrect = true;
        }
        return figureList;
    }
}
