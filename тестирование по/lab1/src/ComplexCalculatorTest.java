import org.junit.Test;
import static org.junit.Assert.*;

public class ComplexCalculatorTest {

    // Тесты для сложения комплексных чисел
    @Test
    public void testAdditionCorrectValues() {
        ComplexCalculator num1 = new ComplexCalculator(2, 3);
        ComplexCalculator num2 = new ComplexCalculator(1, 2);
        ComplexCalculator sum = num1.add(num2);
        assertEquals(new ComplexCalculator(3, 5), sum);
    }

    @Test(expected = NullPointerException.class)
    public void testAdditionNullValues() {
        ComplexCalculator num1 = new ComplexCalculator(2, 3);
        ComplexCalculator sum = num1.add(null);
    }

    @Test
    public void testAdditionZeroValues() {
        ComplexCalculator num1 = new ComplexCalculator(0, 0);
        ComplexCalculator num2 = new ComplexCalculator(0, 0);
        ComplexCalculator sum = num1.add(num2);
        assertEquals(new ComplexCalculator(0, 0), sum);
    }

    // Тесты для вычитания комплексных чисел
    @Test
    public void testSubtractionCorrectValues() {
        ComplexCalculator num1 = new ComplexCalculator(2, 3);
        ComplexCalculator num2 = new ComplexCalculator(1, 2);
        ComplexCalculator diff = num1.subtract(num2);
        assertEquals(new ComplexCalculator(1, 1), diff);
    }

    @Test(expected = NullPointerException.class)
    public void testSubtractionNullValues() {
        ComplexCalculator num1 = new ComplexCalculator(2, 3);
        ComplexCalculator diff = num1.subtract(null);
    }

    @Test
    public void testSubtractionZeroValues() {
        ComplexCalculator num1 = new ComplexCalculator(0, 0);
        ComplexCalculator num2 = new ComplexCalculator(0, 0);
        ComplexCalculator diff = num1.subtract(num2);
        assertEquals(new ComplexCalculator(0, 0), diff);
    }

    // Тесты для умножения комплексных чисел
    @Test
    public void testMultiplicationCorrectValues() {
        ComplexCalculator num1 = new ComplexCalculator(2, 3);
        ComplexCalculator num2 = new ComplexCalculator(1, 2);
        ComplexCalculator product = num1.multiply(num2);
        assertEquals(new ComplexCalculator(-4, 7), product);
    }

    @Test(expected = NullPointerException.class)
    public void testMultiplicationNullValues() {
        ComplexCalculator num1 = new ComplexCalculator(2, 3);
        ComplexCalculator product = num1.multiply(null);
    }

    @Test
    public void testMultiplicationZeroValues() {
        ComplexCalculator num1 = new ComplexCalculator(0, 0);
        ComplexCalculator num2 = new ComplexCalculator(0, 0);
        ComplexCalculator product = num1.multiply(num2);
        assertEquals(new ComplexCalculator(0, 0), product);
    }

    // Тесты для деления комплексных чисел
    @Test
    public void testDivisionCorrectValues() {
        ComplexCalculator num1 = new ComplexCalculator(2, 3);
        ComplexCalculator num2 = new ComplexCalculator(1, 2);
        ComplexCalculator quotient = num1.divide(num2);
        assertEquals(new ComplexCalculator(1.6, -0.2), quotient);
    }

    @Test(expected = NullPointerException.class)
    public void testDivisionNullValues() {
        ComplexCalculator num1 = new ComplexCalculator(2, 3);
        ComplexCalculator quotient = num1.divide(null);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivisionZeroDenominator() {
        ComplexCalculator num1 = new ComplexCalculator(2, 3);
        ComplexCalculator num2 = new ComplexCalculator(0, 0);
        ComplexCalculator quotient = num1.divide(num2);
    }
}
