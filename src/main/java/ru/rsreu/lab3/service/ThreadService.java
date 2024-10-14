package ru.rsreu.lab3.service;

import ru.rsreu.lab3.entity.LazyResult;
import ru.rsreu.lab3.entity.ResultWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for managing threads that perform integral calculations.
 * Provides functionality to start, stop, and await threads.
 */
public class ThreadService {
    private static ThreadService instance;
    private final Map<Integer, Thread> threadMap;
    private int id;

    /**
     * Private constructor for initializing the ThreadService.
     * Initializes the thread map and the thread ID counter.
     */
    private ThreadService() {
        this.threadMap = new HashMap<>();
        this.id = 0;
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
    public void startThread(double epsilon, int size) {
        double a = 0;
        double b = 1;
        FunctionWrapper function = new FunctionWrapper(x -> Math.sin(x) * x, a, b, epsilon);
        LazyResult lazyResult = LazyResult.getInstance(function.calculateInitialResult(), size);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int start = i;
            Thread thread = new Thread(() -> {
                IntegralCalculator calculator = new IntegralCalculator(function, lazyResult);
                calculator.calculate(start, size);
            });
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        lazyResult.setResult(function.calculateFinalResult(lazyResult.getResult()));
        System.out.println(new ResultWrapper(lazyResult.getResult()));
    }

    /**
     * Stops the thread with the specified ID.
     * If the thread is still running, it is interrupted and removed from the thread map.
     *
     * @param id The ID of the thread to stop.
     */
    public void stopThread(int id) {
        Thread thread = this.threadMap.get(id);
        if (thread != null) {
            if (thread.isAlive()) {
                thread.interrupt();
                this.threadMap.remove(id);
            }
            return;
        }
        System.out.println("Потока с id = " + id + " не существует");
    }

    /**
     * Waits for the thread with the specified ID to finish.
     *
     * @param id The ID of the thread to wait for.
     */
    public boolean awaitThread(int id) {
        Thread thread = this.threadMap.get(id);
        try {
            if (thread != null) {
                thread.join();
            } else {
                System.out.println("Потока с id = " + id + " не существует");
            }
        } catch (InterruptedException e) {
            System.out.println("Основной поток был прерван. Завершение работы программы");
            return false;
        }
        return true;
    }
}
