package ru.rsreu.parallel.concurrent;

import java.util.concurrent.TimeUnit;

public class Semaphore {
    private int permits;

    public Semaphore(int permits) {
        if (permits < 0) {
            throw new IllegalArgumentException("Количество разрешений должно быть неотрицательным");
        }
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permits == 0) {
            wait();
        }
        permits--;
    }

    public synchronized void release() {
        permits++;
        notify();
    }

    public synchronized boolean tryAcquire() {
        if (permits > 0) {
            permits--;
            return true;
        }
        return false;
    }

    public synchronized boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException {
        long nanosTimeout = unit.toNanos(timeout);
        long endTime = System.nanoTime() + nanosTimeout;

        while (permits == 0) {
            if (nanosTimeout <= 0) {
                return false;
            }
            TimeUnit.NANOSECONDS.timedWait(this, nanosTimeout);
            nanosTimeout = endTime - System.nanoTime();
        }

        permits--;
        return true;
    }

    public synchronized int availablePermits() {
        return permits;
    }
}
