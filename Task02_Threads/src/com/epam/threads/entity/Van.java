package com.epam.threads.entity;

import com.epam.threads.common_resourse.LogisticBaseSingleton;
import com.epam.threads.exception.InvalidArgumentException;
import com.epam.threads.exception.NullArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Entity class for define van. Implements {@link Callable} to create thread of
 * vans.
 */
public class Van implements Callable<Optional<Object>> {
    /**
     * Logger for logging errors.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Van.class);

    /**
     * Static field to define vanId for new object.
     */
    private static int idCounter = 0;

    /**
     * Van id.
     */
    private long vanId;

    /**
     * Field shows target to arrive in base.
     */
    private String target;

    /**
     * Field shows van status in order.
     */
    private String status;

    /**
     * String defines express status in order.
     */
    private static final String EXPRESS_STATUS = "express";

    /**
     * String defines common status in order.
     */
    private static final String COMMON_STATUS = "common";


    /**
     * Constructor - initialize target of van and status.
     *
     * @param arriveTarget arrive target.
     * @param vanStatus    van status.
     * @throws InvalidArgumentException when target or status is invalid.
     * @throws NullArgumentException    when target or status is null pointer.
     */
    public Van(final String arriveTarget, final String vanStatus)
            throws InvalidArgumentException, NullArgumentException {
        validate(arriveTarget, vanStatus);
        this.target = arriveTarget;
        this.status = vanStatus;
        vanId = ++idCounter;
    }

    /**
     * Method runs when {@link java.util.concurrent.ExecutorService} call it.
     * Show info for user how van is acting in base.
     *
     * @return this object.
     * @see java.util.concurrent.ExecutorService
     */
    @Override
    public Optional<Object> call() {
        if (status.equals(EXPRESS_STATUS)) {
            System.out.println("Express van number " + vanId
                    + " arrived to base.");
        } else {
            System.out.println("Van number " + vanId + " arrived to base.");
        }
        int terminalNum = LogisticBaseSingleton.INSTANCE.takeTerminal();
        delay(1);
        System.out.println("Van number " + vanId + " took a terminal "
                + terminalNum + ".");
        if (target.equals("load")) {
            System.out.println("Van's " + vanId + " loading...");
        } else {
            if (status.equals(EXPRESS_STATUS)) {
                System.out.println("Express van's " + vanId + " unloading...");
            } else {
                System.out.println("Van's " + vanId + " unloading...");
            }
        }
        delay(2);
        if (status.equals(EXPRESS_STATUS)) {
            System.out.println("Express van " + vanId + " leaved terminal "
                    + terminalNum + ".");
        } else {
            System.out.println("Van " + vanId + " leaved terminal "
                    + terminalNum + ".");
        }
        try {
            LogisticBaseSingleton.INSTANCE.leaveTerminal(terminalNum);
        } catch (InvalidArgumentException e) {
            LOGGER.error("Invalid terminal number.", e);
        }
        delay(1);
        return Optional.of(this);
    }

    /**
     * Sets delay for call method.
     *
     * @param timeout delay time.
     */
    private void delay(final int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            LOGGER.error("The thread is interrupted when sleep.", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Checks input data to be valid.
     *
     * @param arriveTarget arrive target.
     * @param vanStatus    van status.
     * @throws InvalidArgumentException when target or status is invalid.
     * @throws NullArgumentException    when target or status is null pointer.
     */
    private void validate(final String arriveTarget, final String vanStatus)
            throws InvalidArgumentException, NullArgumentException {
        if (arriveTarget == null || vanStatus == null) {
            throw new NullArgumentException("Vans argument is null");
        }
        if ((!arriveTarget.equals("load"))
                && (!arriveTarget.equals("unload"))) {
            throw new InvalidArgumentException("Invalid string target.");
        }
        if (!vanStatus.equals(COMMON_STATUS)
                && (!vanStatus.equals(EXPRESS_STATUS))) {
            throw new InvalidArgumentException("Invalid string status.");
        }
    }
}
