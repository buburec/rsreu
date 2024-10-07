package ru.rsreu.lab3.service;

import ru.rsreu.lab3.entity.LazyResult;
import ru.rsreu.lab3.entity.ResultWrapper;

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
    private final LazyResult lazyResult;
    private final FunctionWrapper function;

    public IntegralCalculator(FunctionWrapper function, LazyResult lazyResult) {
        this.lazyResult = lazyResult;
        this.function = function;
    }

    public void calculate(int start, int offset) {
        long n = this.function.getN();
        for (int i = start; i < n; i += offset) {
            if (Thread.interrupted()) {
                return;
            }
            double x = this.function.calculateX(i);
            synchronized (this.lazyResult) {
                this.lazyResult.updateResult(this.function.apply(x));
                if (i % (n / 20) == 0)
                    this.lazyResult.updateProgress(Integer.parseInt(Thread.currentThread().getName()), (double) i / n);
            }
        }
    }

}
