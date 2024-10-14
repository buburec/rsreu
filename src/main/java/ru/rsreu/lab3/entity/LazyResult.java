package ru.rsreu.lab3.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LazyResult {
    private double result;
    private final double[] parts;
    private static volatile LazyResult INSTANCE;

    private LazyResult(double result, int size) {
        this.result = result;
        this.parts = new double[size];
    }

    public static LazyResult getInstance(double result, int size) {
        if (INSTANCE == null) {
            synchronized (LazyResult.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazyResult(result, size);
                    System.out.println("Создано хранилище с ленивой инициализацией.");
                }
            }
        }
        return INSTANCE;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public synchronized void updateResult(double updated) {
        this.result += updated;
    }

    public synchronized void updateProgress(int threadId, double part) {
        this.parts[threadId] = part;
        print();
    }

    private void print() {
        double overallProgress = Arrays.stream(this.parts).average().getAsDouble() * 100;
        System.out.printf("Общий прогресс: %.2f%%%n", overallProgress);
    }
}

