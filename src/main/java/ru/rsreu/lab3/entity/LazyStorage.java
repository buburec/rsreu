package ru.rsreu.lab3.entity;

import ru.rsreu.lab3.ApplicationContext;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LazyStorage {
    private static volatile LazyStorage INSTANCE;
    private final double[] threadProgress;
    private final Lock lock;

    private LazyStorage() {
        this.threadProgress = new double[ApplicationContext.getThreadPoolSize()];
        this.lock = new ReentrantLock();
    }

    public static LazyStorage getInstance() {
        if (INSTANCE == null) {
            synchronized (LazyStorage.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazyStorage();
                    System.out.println("Создано хранилище с ленивой инициализацией.");
                }
            }
        }
        return INSTANCE;
    }

    public void update(int threadId, double part) {
        lock.lock();
        try {
            this.threadProgress[threadId] = part;
            double overallProgress = Arrays.stream(this.threadProgress).average().orElseThrow() * 100;
            System.out.printf("Общий прогресс: %.2f%%%n", overallProgress);
        } finally {
            lock.unlock();
        }
    }
}

