package com.epam.threads.runner;

import com.epam.threads.common_resourse.LogisticBaseSingleton;
import com.epam.threads.controller.OrderController;
import com.epam.threads.entity.Van;
import com.epam.threads.exception.InvalidArgumentException;
import com.epam.threads.exception.NullArgumentException;
import com.epam.threads.parser.FigureParser;
import com.epam.threads.reader.Reader;
import com.epam.threads.thread_factory.VanFactory;
import com.epam.threads.thread_factory.VanFactoryImpl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Runner {
    private static final String filePath = "data" + File.separator
            + "source.txt";

    public static void main(String[] args) throws NullArgumentException, InvalidArgumentException {
        Reader fileReader = new Reader(filePath);
        FigureParser parser = new FigureParser();
        List<Integer> fileData= parser.parseInteger(fileReader.getStringList());

        VanFactory<Van> vanFactory = new VanFactoryImpl();
        Map<Integer, Queue<Van>> queueMap = vanFactory.createVanQueueMap(fileData);

        LogisticBaseSingleton.INSTANCE.setOrderController(
                new OrderController(queueMap));
        LogisticBaseSingleton.INSTANCE.startWork(true);
    }
}
