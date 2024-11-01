package ru.rsreu.parallel.concurrent;

import java.util.concurrent.TimeUnit;

public class CountDownLatch {
    private int count;

    public CountDownLatch(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Счётчик должен быть неотрицательным");
        }
        this.count = count;
    }

    public synchronized void countDown() {
        if (count > 0) {
            count--;
            if (count == 0) {
                notifyAll();
            }
        }
    }

    public synchronized void await() throws InterruptedException {
        while (count > 0) {
            wait();
        }
    }

    public synchronized boolean tryAwait(long timeout, TimeUnit unit) throws InterruptedException {
        long nanosTimeout = unit.toNanos(timeout);
        long endTime = System.nanoTime() + nanosTimeout;

        while (count > 0) {
            if (nanosTimeout <= 0) {
                return false;
            }
            TimeUnit.NANOSECONDS.timedWait(this, nanosTimeout);
            nanosTimeout = endTime - System.nanoTime();
        }
        return true;
    }

    public int getCount() {
        return count;
    }
}
