package com.epam.threads.common_resourse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LogisticBaseSingleton {
    private static final Logger LOGGER
            = LogManager.getLogger(LogisticBaseSingleton.class);

    private static final int terminals = 10;
    private int freeTerminals;

    public static final LogisticBaseSingleton INSTANCE
            = new LogisticBaseSingleton();


    private LogisticBaseSingleton() {
        freeTerminals = terminals;
    }

    public boolean takeTerminal() {
        if (freeTerminals <= 0) {
            return false;
        }
        --freeTerminals;
        return true;
    }

    public void leaveTerminal() {
        ++freeTerminals;
    }
}
