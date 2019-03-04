package com.epam.threads.factory;

import com.epam.threads.entity.Van;
import com.epam.threads.exception.InvalidArgumentException;
import com.epam.threads.exception.NullArgumentException;

import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Interface for create hierarchy of creators.
 *
 * @param <T> type of the class.
 */
public interface VanFactory<T extends Van> {
    /**
     * @param data data read from file.
     * @return map of queues of different statuses.
     * @throws InvalidArgumentException when method argument is invalid.
     * @throws NullArgumentException    when method argument is null.
     */
    Map<Integer, Queue<T>> createVanQueueMap(
            List<Integer> data) throws InvalidArgumentException,
            NullArgumentException;
}
