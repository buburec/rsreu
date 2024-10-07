//import org.junit.jupiter.api.Test;
//import ru.rsreu.lab3.service.FunctionWrapper;
//import ru.rsreu.lab3.service.IntegralCalculator;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Test class for the {@link IntegralCalculator}.
// * It includes tests for calculating integrals of different functions
// * and measuring execution time for different levels of accuracy.
// * @version 1.0
// * @author Kirill Popov
// */
//public class IntegralCalculatorTest {
//
//    /**
//     * Tests the integral of the function sin(x) * x from 0 to 1 using an epsilon of 1e-6.
//     * The expected result is approximately 0.3011686789.
//     */
//    @Test
//    public void testSinXTimesX() {
//        IntegralCalculator calculator = new IntegralCalculator(1e-6);
//        double result = calculator.calculate(new FunctionWrapper(x -> Math.sin(x) * x), 0, 1);
//        assertEquals(0.3011686789, result, 1e-6);
//    }
//
//    /**
//     * Tests the integral of the function x^2 from 0 to 1 using an epsilon of 1e-6.
//     * The expected result is exactly 1/3 (0.3333...).
//     */
//    @Test
//    public void testXSquared() {
//        IntegralCalculator calculator = new IntegralCalculator(1e-6);
//        double result = calculator.calculate(new FunctionWrapper(x -> x * x), 0, 1);
//        assertEquals(1.0 / 3.0, result, 1e-6);
//    }
//
//    /**
//     * Tests the execution time of the integral of sin(x) * x from 0 to 1 for various levels of accuracy.
//     * The test measures how long it takes to compute the integral for different epsilon values,
//     * ensuring the computation takes between 1 and 10 seconds for each epsilon.
//     */
//    @Test
//    public void testExecutionTime() {
//        double a = 0;
//        double b = 1;
//        double[] epsilons = {1e-18};
//
//        for (double epsilon : epsilons) {
//            IntegralCalculator calculator = new IntegralCalculator(epsilon);
//
//            long startTime = System.nanoTime();
//            double result = calculator.calculate(new FunctionWrapper(x -> Math.sin(x) * x), a, b);
//            long endTime = System.nanoTime();
//
//            double duration = (endTime - startTime) / 1e9;
//            System.out.printf("Погрешность: %e, Результат: %f, Время: %f секунд%n", epsilon, result, duration);
//            assertTrue(duration < 20.0);
//        }
//    }
//}
