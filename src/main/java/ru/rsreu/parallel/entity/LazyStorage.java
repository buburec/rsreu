package ru.rsreu.parallel.entity;

import ru.rsreu.parallel.ApplicationContext;
import ru.rsreu.parallel.concurrent.Lock;

import java.util.Arrays;


public class LazyStorage {
    private static volatile LazyStorage INSTANCE;
    private final double[] threadProgress;
    private final Lock lock;

    private LazyStorage() {
        this.threadProgress = new double[ApplicationContext.getThreadPoolSize()];
        this.lock = new Lock();
    }

    public static LazyStorage getInstance() {
        if (INSTANCE == null) {
            synchronized (LazyStorage.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazyStorage();
                    System.out.println("Создано хранилище с ленивой инициализацией");
                }
            }
        }
        return INSTANCE;
    }

    public void update(int threadId, double part) throws InterruptedException {
        lock.lock();
        double overallProgress = 0d;
        try {
            this.threadProgress[threadId] = part;
            overallProgress = Arrays.stream(this.threadProgress).average().orElseThrow() * 100;

        } finally {
            lock.unlock();
            System.out.printf("Общий прогресс: %.2f%%%n", overallProgress);
        }
    }
}

