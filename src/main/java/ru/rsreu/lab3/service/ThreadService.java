package ru.rsreu.lab3.service;

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

    /**
     * Private constructor for initializing the ThreadService.
     * Initializes the thread map and the thread ID counter.
     */
    private ThreadService(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
        this.executor = Executors.newFixedThreadPool(threadPoolSize);
    }

    /**
     * Returns the singleton instance of the ThreadService.
     *
     * @return The singleton instance of ThreadService.
     */
    public static ThreadService getInstance(int threadPoolSize) {
        instance = instance == null ? new ThreadService(threadPoolSize) : instance;
        return instance;
    }

    /**
     * Starts a new thread that calculates an integral for the specified epsilon.
     * The calculation is performed over the function f(x) = sin(x) * x on the interval [0, 1].
     *
     * @param epsilon The precision value for the integral calculation.
     */
    public void startThreadPool(double epsilon) {
        double a = 0;
        double b = 1;
        FunctionWrapper function = new FunctionWrapper(x -> Math.sin(x) * x, a, b, epsilon);
        IntegralCalculator calculator = new IntegralCalculator(function, threadPoolSize);
        List<Callable<Double>> tasks = new ArrayList<>();
        for (int i = 0; i < threadPoolSize; i++) {
            int start = i;
            tasks.add(() -> calculator.calculate(start, threadPoolSize));
        }
        try {
            Double result = function.calculateInitialResult();
            List<Future<Double>> futures = this.executor.invokeAll(tasks);
            for (Future<Double> future : futures) {
                result += future.get();
            }
            System.out.println(new ResultWrapper(function.calculateFinalResult(result)));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopThreadPool() {
        executor.shutdown();
    }
}
