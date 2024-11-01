package ru.rsreu.parallel;

import ru.rsreu.parallel.concurrent.Semaphore;

public class ApplicationContext {
    private static int threadPoolSize;
    private static Semaphore semaphore;

    private ApplicationContext() {}

    protected static void initializeContext(int threadPoolSize, int asyncSize) {
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
