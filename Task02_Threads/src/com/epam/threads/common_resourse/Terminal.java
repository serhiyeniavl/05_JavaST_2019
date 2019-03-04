package com.epam.threads.common_resourse;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class helper to create terminal in {@link LogisticBaseSingleton}.
 *
 * @see Lock
 */
class Terminal {
    /**
     * Locker.
     */
    private Lock locker;

    /**
     * Terminal status.
     */
    private boolean isFree = true;

    /**
     * Constructor - initialize locker.
     */
    Terminal() {
        locker = new ReentrantLock();
    }

    /**
     * Checks terminal status.
     *
     * @return terminal status.
     */
    boolean isFree() {
        return isFree;
    }

    /**
     * Sets terminal status.
     *
     * @param free status to set.
     */
    void setFree(final boolean free) {
        locker.lock();
        isFree = free;
        locker.unlock();
    }
}
