package ru.rsreu.lab3.service;

import ru.rsreu.lab3.ApplicationContext;
import ru.rsreu.lab3.entity.LazyStorage;

/**
 * The IntegralCalculator class is responsible for calculating definite integrals
 * using the trapezoidal rule. The accuracy of the calculation is controlled by the epsilon value,
 * which is provided during the class initialization.
 *
 * <p>This class includes methods for estimating the necessary number of intervals
 * based on the desired accuracy, as well as for reporting progress during the computation.</p>
 *
 * @version 1.0
 * @author Kirill Popov
 */
public class IntegralCalculator {
    private final FunctionWrapper function;
    private static IntegralCalculator instance;

    public IntegralCalculator(FunctionWrapper function) {
        this.function = function;
    }

    public double calculate(int start, int offset) {
        double result = 0.0;
        long n = this.function.getN();
        int counter = 0;
        for (int i = start; i < n; i += offset) {
            if (Thread.interrupted()) {
                return 0d;
            }
            double x = this.function.calculateX(i);
            result += this.function.apply(x);
            if (counter % (n / 20) == 0) {
                LazyStorage.getInstance().update(start, (double) i / n);
            }
            counter++;
        }
        return result;
    }

}
