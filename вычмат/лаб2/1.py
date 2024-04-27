import sys
from PyQt6 import uic
from PyQt6.QtWidgets import QApplication, QMainWindow
import numpy as np
import matplotlib.pyplot as plt


class Lab2(QMainWindow):
    def __init__(self):
        super().__init__()
        uic.loadUi('main.ui', self)
        self.pushButton.clicked.connect(self.calculate_function)
        

    def function(self, x):
        return x**5 + x - 0.2
    
    
    def derivative_function(self, x):
        return 5 * x**4 + 1


    def second_derivative_function(self, x):
        return 20 * x**3
    

    def iterative_function(self, x):
        return x - self.function(x) / 6


    def calculate_function(self):
        if self.radioButtonBinary.isChecked():
            self.bisection_method(self.function, self.doubleSpinBox_a.value(), self.doubleSpinBox_b.value())
        elif self.radioButtonChord.isChecked():
            self.secant_method(self.function, self.second_derivative_function, self.doubleSpinBox_a.value(), self.doubleSpinBox_b.value())
        elif self.radioButtonTangents.isChecked():
            self.newton_method(self.function, self.derivative_function, self.doubleSpinBox_b.value())
        elif self.radioButtonNewtonModified.isChecked():
            self.modified_newton_method(self.function, self.derivative_function, self.doubleSpinBox_b.value())
        elif self.radioButtonCombined.isChecked():
            self.combined_method(self.function, self.derivative_function, self.doubleSpinBox_a.value(), self.doubleSpinBox_b.value())
        else:
            self.iteration_method(self.iterative_function, self.doubleSpinBox_b.value())


    def bisection_method(self, func, a, b, eps=1e-4, max_iter=100):
        if func(a) * func(b) >= 0:
            self.labelResult.setText("Невозможно найти корень на этом интервале.")
            return None
        c = c_prev = 0
        for _ in range(max_iter):
            c_new = (a + b) / 2
            if (c_new - c)**2 / abs(2 * c - c_new - c_prev) < eps:  
                self.labelResult.setText(f"Положительный корень: {c_new}")
                return c_new  
            c_prev, c = c, c_new
            if func(c_new) * func(a) < 0:
                b = c_new
            else:
                a = c_new
        self.labelResult.setText("Достигнуто максимальное количество итераций.")    
        return None

    
    def secant_method(self, func, second_derivative, a, b, eps=1e-4, max_iter=100):
        if func(a) * func(b) >= 0:
            self.labelResult.setText("Невозможно найти корень на этом интервале.")
            return None
        c = c_prev = 0
        for _ in range(max_iter):
            c_new = a - (func(a) * (b - a)) / (func(b) - func(a))
            if (c_new - c)**2 / abs(2 * c - c_new - c_prev) < eps:  
                self.labelResult.setText(f"Положительный корень: {c_new}")
                return c_new 
            c_prev, c = c, c_new
            if func(c_new) * second_derivative(a) > 0:
                a = c_new
            else:
                b = c_new
        self.labelResult.setText("Достигнуто максимальное количество итераций.")   
        return None
    

    def newton_method(self, func, derivative, x0, eps=1e-4, max_iter=100):
        x = x0
        for _ in range(max_iter):
            x_new = func(x)
            if abs(x_new) < eps:  
                self.labelResult.setText(f"Положительный корень: {x}")
                return x 
            x = x - x_new / derivative(x)
        self.labelResult.setText("Достигнуто максимальное количество итераций.")  
        return None


    def modified_newton_method(self, func, derivative, x0, eps=1e-4, max_iter=100):
        x = x0
        derivative_x0 = derivative(x0)
        for _ in range(max_iter):
            x_new = func(x)
            if abs(x_new) < eps:
                self.labelResult.setText(f"Положительный корень: {x}")
                return x 
            x = x - x_new / derivative_x0
        self.labelResult.setText("Достигнуто максимальное количество итераций.")  
        return None


    def combined_method(self, func, derivative, a, b, eps=1e-4, max_iter=100):
        if func(a) * func(b) >= 0:
            self.labelResult.setText("Невозможно найти корень на этом интервале.")
            return None
        for _ in range(max_iter):
            a = a - func(a) / derivative(a)
            if abs(a - b) < eps:
                self.labelResult.setText(f"Положительный корень: {a}")
                return a
            b = b - func(b) * (a - b) / (func(a) - func(b))
        self.labelResult.setText("Достигнуто максимальное количество итераций.")  
        return None
    

    def iteration_method(self, phi, x0, eps=1e-4, max_iter=100):
        x, x_prev = x0, 0
        for _ in range(max_iter):
            x_new = phi(x)
            if (x_new - x)**2 / abs(2 * x - x_new - x_prev) < eps:      
                self.labelResult.setText(f"Положительный корень: {x_new}")
                return x_new  
            x, x_prev = x_new, x
        self.labelResult.setText("Достигнуто максимальное количество итераций.")
        return None
    

if __name__ == '__main__':
    application = QApplication(sys.argv)
    executable = Lab2()
    executable.show()
    
    # # Включаем интерактивный режим
    # plt.ion()

    # # Задаем диапазон значений x
    # x = np.linspace(-1, 1, 400)

    # # Вычисляем значения функции для каждого значения x
    # y = x**5 + x - 0.2

    # # Строим график
    # plt.figure(figsize=(8, 6))
    # plt.plot(x, y, label='$x^5 + x - 0.2$')
    # plt.title('График функции $x^5 + x - 0.2$')
    # plt.xlabel('x')
    # plt.ylabel('y')

    # # Устанавливаем пересекающиеся оси по центру
    # plt.gca().spines['left'].set_position('center')
    # plt.gca().spines['bottom'].set_position('center')
    # plt.gca().spines['right'].set_color('none')
    # plt.gca().spines['top'].set_color('none')

    # # Устанавливаем меньший масштаб
    # plt.gca().set_aspect(0.5)

    # plt.grid(True)
    # plt.legend()

    # # Показываем график
    # plt.show()

    # # Остановка программы для интерактивного режима
    # plt.ioff()
    # plt.show()

    sys.exit(application.exec())