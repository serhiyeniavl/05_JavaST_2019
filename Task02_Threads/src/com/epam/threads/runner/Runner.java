package com.epam.threads.runner;

import com.epam.threads.common_resourse.LogisticBaseSingleton;
import com.epam.threads.controller.OrderController;
import com.epam.threads.entity.Van;
import com.epam.threads.exception.InvalidArgumentException;
import com.epam.threads.exception.NullArgumentException;
import com.epam.threads.io.Writer;
import com.epam.threads.parser.FigureParser;
import com.epam.threads.io.Reader;
import com.epam.threads.factory.VanFactory;
import com.epam.threads.factory.VanFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Class for store initial point in program.
 */
public final class Runner {
    /**
     * Logger for storing error causes.
     */
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    /**
     * Path to source file.
     */
    private static final String FILE_PATH = "data" + File.separator
            + "source.txt";

    /**
     * Private constructor.
     */
    private Runner() {
    }

    /**
     * Main method of the program. Initial point.
     *
     * @param args program arguments.
     */
    public static void main(final String[] args) {
        Writer fileWriter;
        Reader fileReader;
        List<Integer> fileData = null;
        FigureParser parser = new FigureParser();
        try {
            fileWriter = new Writer(FILE_PATH);
            final int dataInputQuantity = 3;
            final int randomRange = 10;
            fileWriter.writeRandomInteger(dataInputQuantity, randomRange);
        } catch (NullArgumentException e) {
            LOGGER.error("File path is null.", e);
        } catch (InvalidArgumentException e) {
            LOGGER.error("File path is incorrect.", e);
        }


        try {
            fileReader = new Reader(FILE_PATH);
            fileData = parser.parseInteger(
                    fileReader.getStringList());
        } catch (NullArgumentException e) {
            LOGGER.error("File path is null.", e);
        }

        VanFactory<Van> vanFactory = new VanFactoryImpl();
        Map<Integer, Queue<Van>> queueMap = null;

        try {
            queueMap = vanFactory
                    .createVanQueueMap(fileData);
        } catch (InvalidArgumentException e) {
            LOGGER.error("Argument is invalid.", e);
        } catch (NullArgumentException e) {
            LOGGER.error("Argument is null.", e);
        }

        try {
            LogisticBaseSingleton.INSTANCE.setOrderController(
                    new OrderController(queueMap));
        } catch (NullArgumentException e) {
            LOGGER.error("", e);
        }

        LogisticBaseSingleton.INSTANCE.startWork(true);
    }
}
