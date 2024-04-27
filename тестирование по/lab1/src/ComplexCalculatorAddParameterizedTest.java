import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ComplexCalculatorAddParameterizedTest {
    private final ComplexCalculator num1;
    private final ComplexCalculator num2;
    private final ComplexCalculator expectedSum;

    public ComplexCalculatorAddParameterizedTest(ComplexCalculator num1, ComplexCalculator num2, ComplexCalculator expectedSum) {
        this.num1 = num1;
        this.num2 = num2;
        this.expectedSum = expectedSum;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new ComplexCalculator(2, 3), new ComplexCalculator(1, 2), new ComplexCalculator(3, 5)},
                {new ComplexCalculator(-2, 3), new ComplexCalculator(1, -2), new ComplexCalculator(-1, 1)},
                {new ComplexCalculator(0, 0), new ComplexCalculator(1, 2), new ComplexCalculator(1, 2)},
                {new ComplexCalculator(2, 3), new ComplexCalculator(0, 0), new ComplexCalculator(2, 3)}
        });
    }

    @Test
    public void testAddition() {
        ComplexCalculator sum = num1.add(num2);
        assertEquals(expectedSum, sum);
    }
}
