package com.epam.threads.common_resourse;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Terminal {
    private Lock lock;
    private boolean isFree = true;

    protected Terminal() {
        lock = new ReentrantLock();
    }

    protected boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        lock.lock();
        isFree = free;
        lock.unlock();
    }
}
