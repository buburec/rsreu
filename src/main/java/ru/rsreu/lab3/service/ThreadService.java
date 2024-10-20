package ru.rsreu.lab3.service;

import ru.rsreu.lab3.ApplicationContext;
import ru.rsreu.lab3.entity.LazyStorage;
import ru.rsreu.lab3.entity.ResultWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Service for managing threads that perform integral calculations.
 * Provides functionality to start, stop, and await threads.
 */
public class ThreadService {
    private static ThreadService instance;
    private final int threadPoolSize;
    private final ExecutorService executor;
    private final Semaphore semaphore;
    private final CountDownLatch countDownLatch;

    /**
     * Private constructor for initializing the ThreadService.
     * Initializes the thread map and the thread ID counter.
     */
    private ThreadService() {
        this.threadPoolSize = ApplicationContext.getThreadPoolSize();
        this.executor = Executors.newCachedThreadPool();
        this.semaphore = ApplicationContext.getSemaphore();
        this.countDownLatch = new CountDownLatch(threadPoolSize);
    }

    /**
     * Returns the singleton instance of the ThreadService.
     *
     * @return The singleton instance of ThreadService.
     */
    public static ThreadService getInstance() {
        instance = instance == null ? new ThreadService() : instance;
        return instance;
    }

    /**
     * Starts a new thread that calculates an integral for the specified epsilon.
     * The calculation is performed over the function f(x) = sin(x) * x on the interval [0, 1].
     *
     * @param epsilon The precision value for the integral calculation.
     */
    public ResultWrapper startThreadPool(double epsilon) {
        double a = 0;
        double b = 1;
        FunctionWrapper function = new FunctionWrapper(x -> Math.sin(x) * x, a, b, epsilon);
        IntegralCalculator calculator = new IntegralCalculator(function);
        List<Callable<Double>> tasks = this.initializeTasks(calculator);

        try {
            Double result = function.calculateInitialResult();
            List<Future<Double>> futures = this.executor.invokeAll(tasks);
            for (Future<Double> future : futures) {
                result += future.get();
            }
            result = function.calculateFinalResult(result);
            return new ResultWrapper(result);
        } catch (InterruptedException e) {
            System.err.println("Попытка прервать остановленный поток");
            return ResultWrapper.NULL_RESULT;
        } catch (ExecutionException e) {
            System.err.println("Произошла ошибка в вычислениях");
            return ResultWrapper.NULL_RESULT;
        }
    }

    public void stopThreadPool() {
        executor.shutdown();
    }

    private List<Callable<Double>> initializeTasks(IntegralCalculator calculator) {
        List<Callable<Double>> tasks = new ArrayList<>();
        for (int i = 0; i < threadPoolSize; i++) {
            int start = i;
            tasks.add(() -> {
                semaphore.acquireUninterruptibly();
                try {
                   return calculator.calculate(start, threadPoolSize);
                } finally {
                    semaphore.release();
                    if (countDownLatch.getCount() == threadPoolSize) {
                       LazyStorage.getInstance().setFirstTimeRelease(System.nanoTime());
                    }
                    long firstTime = LazyStorage.getInstance().getFirstTimeRelease();
                    long timeRelease = System.nanoTime();
                    System.out.printf("Поток-%d завершил работу за %d нc\n", start, (timeRelease - firstTime));
                    countDownLatch.countDown();
                }
            });
        }
        return tasks;
    }
}
