public class ComplexCalculator {
    private double real;
    private double imaginary;

    // Конструктор
    public ComplexCalculator(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // Метод для сложения комплексных чисел
    public ComplexCalculator add(ComplexCalculator other) {
        double newReal = this.real + other.real;
        double newImaginary = this.imaginary + other.imaginary;
        return new ComplexCalculator(newReal, newImaginary);
    }

    // Метод для вычитания комплексных чисел
    public ComplexCalculator subtract(ComplexCalculator other) {
        double newReal = this.real - other.real;
        double newImaginary = this.imaginary - other.imaginary;
        return new ComplexCalculator(newReal, newImaginary);
    }

    // Метод для умножения комплексных чисел
    public ComplexCalculator multiply(ComplexCalculator other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexCalculator(newReal, newImaginary);
    }

    // Метод для деления комплексных чисел
    // Метод для деления комплексных чисел
    public ComplexCalculator divide(ComplexCalculator other) {
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        if (denominator == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        double newReal = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
        double newImaginary = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
        return new ComplexCalculator(newReal, newImaginary);
    }


    // Метод для вывода комплексного числа в строку
    @Override
    public String toString() {
        if (imaginary >= 0) {
            return real + " + " + imaginary + "i";
        } else {
            return real + " - " + (-imaginary) + "i";
        }
    }

    // Метод для получения действительной части комплексного числа
    public double getReal() {
        return real;
    }

    // Метод для получения мнимой части комплексного числа
    public double getImaginary() {
        return imaginary;
    }

    public static void main(String[] args) {
        ComplexCalculator num1 = new ComplexCalculator(2, 3);
        ComplexCalculator num2 = new ComplexCalculator(1, -2);

        System.out.println("Первое комплексное число: " + num1);
        System.out.println("Второе комплексное число: " + num2);

        ComplexCalculator sum = num1.add(num2);
        System.out.println("Сумма: " + sum);

        ComplexCalculator difference = num1.subtract(num2);
        System.out.println("Разность: " + difference);

        ComplexCalculator product = num1.multiply(num2);
        System.out.println("Произведение: " + product);

        ComplexCalculator quotient = num1.divide(num2);
        System.out.println("Частное: " + quotient);
    }
}
