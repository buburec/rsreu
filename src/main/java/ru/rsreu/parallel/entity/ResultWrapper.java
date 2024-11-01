package ru.rsreu.parallel.entity;

import ru.rsreu.parallel.ApplicationContext;

/**
 * Class representing the result of a calculation.
 * It contains the numeric result and a static constant for cases when the calculation was interrupted.
 */
public class ResultWrapper {

    /**
     * The numeric result of the calculation.
     */
    private double result;

    /**
     * Special object used when the calculation was interrupted.
     */
    public static final ResultWrapper NULL_RESULT = new ResultWrapper() {
        @Override
        public String toString() {
            return "Вычисления были прерваны";
        }
    };

    /**
     * Constructs a new ResultCalculation with the specified result.
     *
     * @param result The numeric result of the calculation.
     */
    public ResultWrapper(double result) {
        this.result = result;
    }

    /**
     * Private constructor for the NULL_RESULT constant.
     */
    private ResultWrapper() {
    }

    /**
     * Returns a string representation of the result.
     *
     * @return A string describing the calculation result.
     */
    @Override
    public String toString() {
        return "Результат вычисления: " + this.result;
    }
}
