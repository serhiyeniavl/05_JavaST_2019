package com.epam.info_handling.interpretator;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class that stores expression values.
 */
public class Context {
    /**
     * Values deque.
     */
    private Deque<Integer> contextValue = new ArrayDeque<>();

    /**
     * Poll last element from deque.
     *
     * @return last value in deque.
     */
    public Integer poll() {
        return contextValue.pollLast();
    }

    /**
     * Push element into deque.
     *
     * @param value value to push.
     */
    public void push(final Integer value) {
        contextValue.addLast(value);
    }
}
