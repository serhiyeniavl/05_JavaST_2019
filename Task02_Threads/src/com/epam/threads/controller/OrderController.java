package com.epam.threads.controller;

import com.epam.threads.entity.Van;
import com.epam.threads.exception.IllegalCallException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

public class OrderController {
    private static final Logger LOGGER
            = LogManager.getLogger(OrderController.class);

    private Map<Integer, Queue<Van>> vanQueue;
    private ExecutorService service = null;

    public OrderController(final Map<Integer, Queue<Van>> vans) {
        this.vanQueue = vans;
    }

    public void setExecutorService(final ExecutorService executorService) {
        this.service = executorService;
    }

    public void start() throws IllegalCallException {
        if (service == null) {
            throw new IllegalCallException("Method should've been called"
                    + " with set field service.");
        }
        try {
            service.invokeAll(vanQueue.get(1));
            service.invokeAll(vanQueue.get(0));
        } catch (InterruptedException e) {
            LOGGER.error("Thread interrupted while waiting.", e);
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        service.shutdown();
    }
}
