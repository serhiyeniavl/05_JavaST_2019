package com.epam.threads.common_resourse;

import com.epam.threads.controller.OrderController;
import com.epam.threads.exception.IllegalCallException;
import com.epam.threads.exception.InvalidArgumentException;
import com.epam.threads.exception.NullArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Class singleton for distribution vans by terminals.
 * {@see Terminal}.
 */
public final class LogisticBaseSingleton {
    /**
     * Logger for logging errors.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(LogisticBaseSingleton.class);

    /**
     * Terminal quantity in base.
     */
    private static final int TERMINALS_QUANTITY = 10;

    /**
     *
     */
    public static final LogisticBaseSingleton INSTANCE
            = new LogisticBaseSingleton();

    /**
     * {@link List} of terminals.
     */
    private List<Terminal> terminals;

    /**
     * Order controller for control order.
     */
    private OrderController orderController;


    /**
     * Constructor - initialize terminals list.
     */
    private LogisticBaseSingleton() {
        terminals = new ArrayList<>(TERMINALS_QUANTITY);
        addTerminals();
    }

    /**
     * Sets order controller.
     *
     * @param controller order controller.
     * @throws NullArgumentException when argument controller is null.
     */
    public void setOrderController(final OrderController controller) throws
            NullArgumentException {
        if (controller == null) {
            throw new NullArgumentException("Object OrderController is null");
        }
        this.orderController = controller;
    }

    /**
     * Takes terminal to arrived van in base.
     *
     * @return terminal number.
     */
    public int takeTerminal() {
        int terminalNumber = 1;
        for (Terminal terminal : terminals) {
            if (terminal.isFree()) {
                terminal.setFree(false);
                break;
            }
            ++terminalNumber;
        }
        return terminalNumber;
    }

    /**
     * Sets busied terminal is free by terminal number.
     *
     * @param terminalNum terminal number.
     * @throws InvalidArgumentException when terminal number is invalid.
     */
    public void leaveTerminal(final int terminalNum) throws
            InvalidArgumentException {
        if (terminalNum <= 0 || terminalNum > TERMINALS_QUANTITY) {
            throw new InvalidArgumentException(
                    "Number of terminals out of bound.");
        }
        terminals.get(terminalNum - 1).setFree(true);
    }

    /**
     * Starts work of logic base. Sets executor service in
     * {@link OrderController}. Method can call stopWork method by it own.
     *
     * @param stopWhenVansServiced if this param is true method stops it's work
     *                             when all stuff is serviced.
     */
    public void startWork(final boolean stopWhenVansServiced) {
        orderController.setExecutorService(Executors
                .newFixedThreadPool(TERMINALS_QUANTITY));
        try {
            orderController.start();
        } catch (IllegalCallException e) {
            LOGGER.error("ExecutorService wasn't set in order controller.");
        }
        if (stopWhenVansServiced) {
            stopWork();
        }
    }

    /**
     * Method stops order controller work.
     */
    public void stopWork() {
        orderController.stop();
    }

    /**
     * Additional method to add terminals in list.
     */
    private void addTerminals() {
        for (int i = 0; i < TERMINALS_QUANTITY; i++) {
            terminals.add(new Terminal());
        }
    }
}
