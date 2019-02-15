package com.epam.objects.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class FigureParser {
    private static final Logger LOGGER
            = LogManager.getLogger(FigureParser.class);

    public Map<Integer, List<Double>> parseDouble(final List<String> stringList) {
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
