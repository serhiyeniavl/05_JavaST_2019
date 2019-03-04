package com.epam.threads.factory;

import com.epam.threads.entity.Van;
import com.epam.threads.exception.InvalidArgumentException;
import com.epam.threads.exception.NullArgumentException;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Class inherited from {@link VanFactory}. Creates map of vans queue.
 */
public class VanFactoryImpl implements VanFactory<Van> {
    /**
     * Creates {@link Map} that contains priority and van target.
     *
     * @param data input data.
     * @return queue of vans {@link Map}.
     * @throws InvalidArgumentException when van target or status is invalid.
     * @throws NullArgumentException    when method argument is null.
     */
    @Override
    public Map<Integer, Queue<Van>> createVanQueueMap(final List<Integer> data)
            throws InvalidArgumentException, NullArgumentException {
        Map<Integer, Queue<Van>> vanCallableMapQueue = new HashMap<>();
        ArrayDeque<Van> commonQueue = new ArrayDeque<>();
        ArrayDeque<Van> fastQueue = new ArrayDeque<>();
        final int inputDataAmount = 3;
        for (int i = 0; i < inputDataAmount - 1; i++) {
            for (int j = 0; j < data.get(i); j++) {
                if (j == 0) {
                    commonQueue.add(new Van("load", "common"));
                } else {
                    commonQueue.add(new Van("unload", "common"));
                }
            }
        }
        for (int i = 0; i < data.get(2); i++) {
            fastQueue.add(new Van("unload", "express"));
        }
        vanCallableMapQueue.put(0, commonQueue);
        vanCallableMapQueue.put(1, fastQueue);
        return vanCallableMapQueue;
    }
}
