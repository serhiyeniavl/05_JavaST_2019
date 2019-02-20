package com.epam.objects.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FigureValidator {
    private static final String FIGURE_PATTERN = "-?\\d+([.]\\d*)?";
    private static final String STRING_SPLIT_PATTERN = "\\s+";
    private static final Logger LOGGER
            = LogManager.getLogger(FigureValidator.class);

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
