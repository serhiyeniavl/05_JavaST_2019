package com.epam.objects.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class use for parsing double numbers from {@link List} of {@link String}.
 */
public class FigureParser {

    /**
     * Parsing double numbers form {@link List}.
     * @param stringList list for parsing.
     * @return {@link Map} of parsed doubles.
     */
    public Map<Integer, List<Double>> parseDouble(final List<String>
                                                          stringList) {
        Map<Integer, List<Double>> figureMap = new HashMap<>();
        int objectCounter = 0;
        for (String string : stringList) {
            List<Double> figures = new ArrayList<>();
            for (String figureStr : string.split(" ")) {
                figures.add(Double.parseDouble(figureStr));
            }
            figureMap.put(objectCounter, figures);
            objectCounter++;
        }
        return figureMap;
    }
}
