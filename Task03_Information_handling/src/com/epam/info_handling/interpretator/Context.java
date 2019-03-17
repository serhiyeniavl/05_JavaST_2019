package com.epam.info_handling.interpretator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Context {
    private Deque<Integer> contextValue = new ArrayDeque<>();

    public Integer poll() {
        return contextValue.pollLast();
    }

    public void push(final Integer value) {
        contextValue.addLast(value);
    }
}
