package ru.rsreu.parallel.concurrent;

import java.util.concurrent.TimeUnit;

public class Lock {
    private boolean isLocked = false;
    private Thread lockingThread = null;

    public synchronized void lock() throws InterruptedException {
        while (isLocked && lockingThread != Thread.currentThread()) {
            wait();
        }
        isLocked = true;
        lockingThread = Thread.currentThread();
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == lockingThread) {
            isLocked = false;
            lockingThread = null;
            notify();
        }
    }

    public synchronized boolean tryLock() {
        if (!isLocked || lockingThread == Thread.currentThread()) {
            isLocked = true;
            lockingThread = Thread.currentThread();
            return true;
        }
        return false;
    }

    public synchronized boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        long nanosTimeout = unit.toNanos(timeout);
        long endTime = System.nanoTime() + nanosTimeout;

        while (isLocked && lockingThread != Thread.currentThread()) {
            if (nanosTimeout <= 0) {
                return false;
            }
            TimeUnit.NANOSECONDS.timedWait(this, nanosTimeout);
            nanosTimeout = endTime - System.nanoTime();
        }

        isLocked = true;
        lockingThread = Thread.currentThread();
        return true;
    }

    public synchronized boolean isLocked() {
        return isLocked;
    }
}
