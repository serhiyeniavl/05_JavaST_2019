package com.epam.threads.controller;

import com.epam.threads.entity.Van;
import com.epam.threads.exception.IllegalCallException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

/**
 * Class for control vans thread in
 * {@link com.epam.threads.common_resourse.LogisticBaseSingleton}.
 */
public class OrderController {
    /**
     * Logger for logging errors.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(OrderController.class);

    /**
     * Map of callables.
     */
    private Map<Integer, Queue<Van>> vanQueue;

    /**
     * Service to control.
     */
    private ExecutorService service = null;

    /**
     * Constructor - initialize the map of callbles.
     *
     * @param vans vans queue map.
     */
    public OrderController(final Map<Integer, Queue<Van>> vans) {
        this.vanQueue = vans;
    }

    /**
     * Sets executor service.
     *
     * @param executorService {@link ExecutorService} object.
     */
    public void setExecutorService(final ExecutorService executorService) {
        this.service = executorService;
    }

    /**
     * Stats order controller work. Submits queue of vans.
     *
     * @throws IllegalCallException when service wasn't set early.
     */
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

    /**
     * Stops executor service work.
     */
    public void stop() {
        service.shutdown();
    }
}
