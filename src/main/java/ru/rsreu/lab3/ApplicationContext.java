package ru.rsreu.lab3;

import ru.rsreu.lab3.service.FunctionWrapper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

public class ApplicationContext {
    private static int threadPoolSize;
    private static Semaphore semaphore;

    private ApplicationContext() {}

    public static void initializeContext(int threadPoolSize, int asyncSize) {
        ApplicationContext.threadPoolSize = threadPoolSize;
        ApplicationContext.semaphore = new Semaphore(asyncSize);
    }

    public static int getThreadPoolSize() {
        return threadPoolSize;
    }

    public static Semaphore getSemaphore() {
        return semaphore;
    }
}
