package com.epam.threads.common_resourse;


/**
 * Class helper to create terminal in {@link LogisticBaseSingleton}.
 */
class Terminal {

    /**
     * Terminal status.
     */
    private boolean isFree = true;

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
        isFree = free;
    }
}
