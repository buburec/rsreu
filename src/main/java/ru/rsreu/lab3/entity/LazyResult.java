package ru.rsreu.lab3.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LazyResult {
    private double result;
    private double[] parts;
    private volatile boolean isPrinting = false; // Флаг для предотвращения повторного вывода

    private LazyResult() {
        this.result = 0.0;
    }

    public void setThreadPool(int size) {
        this.parts = new double[size];
    }

    private static class LazyHolder {
        static final LazyResult INSTANCE = new LazyResult();
    }

    public static LazyResult getInstance() {
        return LazyHolder.INSTANCE;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        synchronized (this) {
            this.result = result;
        }
    }

    public void updateResult(double updated) {
        synchronized (this) {
            this.result += updated;
        }
    }

    public void updateProgress(int threadId, double part) {
        synchronized (this) {
            this.parts[threadId] = part;
        }
        tryPrintProgress();
    }

    private void tryPrintProgress() {
        synchronized (this) {
            if (isPrinting) {
                return; // Если другой поток уже выводит, выходим
            }
            isPrinting = true; // Устанавливаем флаг, что начался вывод
        }

        print();

        synchronized (this) {
            isPrinting = false; // Сбрасываем флаг после завершения вывода
        }
    }

    private void print() {
        double totalProgress = 0;

        synchronized (this) {
            for (double progress : parts) {
                totalProgress += progress;
            }
        }

        double overallProgress = (totalProgress / parts.length) * 100; // Средний процент выполнения всех потоков
        System.out.printf("Overall Progress: %.2f%%%n", overallProgress);
    }
}

