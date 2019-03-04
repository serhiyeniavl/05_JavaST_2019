package com.epam.threads.thread_factory;

import com.epam.threads.entity.Van;
import com.epam.threads.exception.InvalidArgumentException;
import com.epam.threads.exception.NullArgumentException;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface VanFactory<T extends Van> {
    Map<Integer, Queue<T>> createVanQueueMap(
            List<Integer> data) throws InvalidArgumentException, NullArgumentException;
}
