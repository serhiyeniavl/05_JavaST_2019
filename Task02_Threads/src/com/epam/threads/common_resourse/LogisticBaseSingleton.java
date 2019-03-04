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

public final class LogisticBaseSingleton {
    private static final Logger LOGGER
            = LogManager.getLogger(LogisticBaseSingleton.class);

    private int terminalsQuantity = 10;

    public static final LogisticBaseSingleton INSTANCE
            = new LogisticBaseSingleton();

    private List<Terminal> terminals;
    private OrderController orderController;


    private LogisticBaseSingleton() {
        terminals = new ArrayList<>(terminalsQuantity);
        addTerminals();
    }

    public void setOrderController(final OrderController controller) throws
            NullArgumentException {
        if (controller == null) {
            throw new NullArgumentException("Object OrderController is null");
        }
        this.orderController = controller;
    }

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

    public void leaveTerminal(final int terminalNum) throws
            InvalidArgumentException {
        if (terminalNum <= 0 || terminalNum > terminalsQuantity) {
            throw new InvalidArgumentException(
                    "Number of terminals out of bound.");
        }
        terminals.get(terminalNum - 1).setFree(true);
    }

    public void startWork(final boolean stopWhenVansServiced) {
        orderController.setExecutorService(Executors
                .newFixedThreadPool(terminalsQuantity));
        try {
            orderController.start();
        } catch (IllegalCallException e) {
            LOGGER.error("ExecutorService wasn't set in order controller.");
        }
        if (stopWhenVansServiced) {
            stopWork();
        }
    }

    public void stopWork() {
        orderController.stop();
    }

    private void addTerminals() {
        for (int i = 0; i < 10; i++) {
            terminals.add(new Terminal());
        }
    }
}
