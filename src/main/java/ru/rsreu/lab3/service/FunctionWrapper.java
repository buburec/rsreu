package ru.rsreu.lab3.service;

import java.util.function.Function;

/**
 * The FunctionWrapper class represents a mathematical function that can be integrated.
 * It encapsulates a functional interface for easy passing and evaluation of functions.
 *
 * @version 1.0
 * @author Kirill Popov
 */
public class FunctionWrapper {

    /**
     * A functional interface representing the mathematical function to be integrated.
     */
    private final Function<Double, Double> function;
    private static final double H = 1e-5;
    private final double a;
    private final double b;
    private final int n;
    private final double h;

    /**
     * Constructs a FunctionWrapper object with the specified function.
     *
     * @param function the mathematical function to integrate, represented as a Function<Double, Double>.
     */
    public FunctionWrapper(Function<Double, Double> function, double a, double b, double epsilon) {
        this.function = function;
        this.a = a;
        this.b = b;
        this.n = this.estimateSplitting(epsilon);
        this.h = this.calculateH();
    }

    /**
     * Applies the stored mathematical function to the specified value of x.
     *
     * @param x the point at which the function should be evaluated.
     * @return the result of applying the function at the given x value.
     */
    public double apply(double x) {
        return this.function.apply(x);
    }


    public double calculateInitialResult() {
        return 0.5 * (this.apply(this.a) + this.apply(this.b));
    }

    public double calculateX(int i) {
        return this.a + i * this.h;
    }

    public double calculateFinalResult(double result) {
        return result * this.h;
    }

    public long getN() {
        return n;
    }

    /**
     * Calculates the first derivative of the stored function at the specified point.
     *
     * @param x the point at which the first derivative should be evaluated.
     * @return the approximate value of the first derivative at the given x value.
     */
    private double applyFirstDerivative(double x) {
        return (function.apply(x + H) - function.apply(x - H)) / (2 * H);
    }

    /**
     * Calculates the second derivative of the stored function at the specified point.
     *
     * @param x the point at which the second derivative should be evaluated.
     * @return the approximate value of the second derivative at the given x value.
     */
    private double applySecondDerivative(double x) {
        return (this.applyFirstDerivative(x + H) - this.applyFirstDerivative(x - H)) / (2 * H);
    }

    private int estimateSplitting(double epsilon) {
        double maxSecondDerivative = Math.abs(this.applySecondDerivative(this.a));
        for (double x = this.a; x <= this.b; x += 0.01) {
            maxSecondDerivative = Math.max(maxSecondDerivative, Math.abs(this.applySecondDerivative(x)));
        }
        return (int) Math.ceil((this.b - this.a) / Math.sqrt((12 * epsilon) / ((this.b - this.a) * maxSecondDerivative)));
    }

    private double calculateH() {
        return (this.b - this.a) / this.n;
    }

}
