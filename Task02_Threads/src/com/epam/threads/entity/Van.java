package com.epam.threads.entity;

import com.epam.threads.common_resourse.LogisticBaseSingleton;
import com.epam.threads.exception.InvalidArgumentException;
import com.epam.threads.exception.NullArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Van implements Callable<Optional<Object>> {
    private static final Logger LOGGER
            = LogManager.getLogger(Van.class);

    private static int idCounter = 0;
    private int id;
    private String target;
    private String status;


    public Van(final String target, final String vanStatus)
            throws InvalidArgumentException, NullArgumentException {
        if (target == null || vanStatus == null) {
            throw new NullArgumentException("Vans argument is null");
        }
        if ((!target.equals("load")) && (!target.equals("unload"))) {
            throw new InvalidArgumentException("Invalid string target.");
        }
        if (!vanStatus.equals("common") && (!vanStatus.equals("express"))) {
            throw new InvalidArgumentException("Invalid string status.");
        }
        this.target = target;
        this.status = vanStatus;
        id = ++idCounter;
    }

    @Override
    public Optional<Object> call() {
        if (status.equals("express")) {
            System.out.println("Express van number " + id
                    + " arrived to base.");
        } else {
            System.out.println("Van number " + id + " arrived to base.");
        }
        int terminalNum = LogisticBaseSingleton.INSTANCE.takeTerminal();
        delay(1);
        System.out.println("Van number " + id + " took a terminal "
                + terminalNum + ".");
        delay(2);
        if (target.equals("load")) {
            System.out.println("Van's " + id + " loading...");
        } else {
            if (status.equals("express")) {
                System.out.println("Express van's " + id + " unloading...");
            } else {
                System.out.println("Van's " + id + " unloading...");
            }
        }
        if (status.equals("express")) {
            System.out.println("Express van " + id + " leaved terminal "
                    + terminalNum + ".");
        } else {
            System.out.println("Van " + id + " leaved terminal " + terminalNum
                    + ".");
        }
        try {
            LogisticBaseSingleton.INSTANCE.leaveTerminal(terminalNum);
        } catch (InvalidArgumentException e) {
            LOGGER.error("Invalid terminal number.", e);
        }
        return Optional.of(this);
    }

    private void delay(final int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            LOGGER.error("The thread is interrupted when sleep.", e);
            Thread.currentThread().interrupt();
        }
    }
}
