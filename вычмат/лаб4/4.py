from PyQt6 import uic
from PyQt6.QtWidgets import QApplication, QMainWindow
import sys
from functools import reduce
import matplotlib.pyplot as plt
import numpy as np
from main_ui import Ui_MainWindow


class Lab4(QMainWindow, Ui_MainWindow):
    def __init__(self):
        super().__init__()
        uic.loadUi('main.ui', self)
        self.pushButton_show.clicked.connect(self.calculate)
        self.radioButton_random.clicked.connect(self.toggle)
        self.radioButton_input.clicked.connect(self.toggle)


    def toggle(self):
        if self.radioButton_random.isChecked():
            self.spinBox_n.setEnabled(True)
            self.lineEdit_args.setEnabled(False)
        else:
            self.spinBox_n.setEnabled(False)
            self.lineEdit_args.setEnabled(True)


    def calculate(self):
        x_values = self.fill_x_values()
        polynomial = self.create_newton_polynomial(x_values, [self.f(x) for x in x_values])
        x_vals = np.arange(self.doubleSpinBox_left.value(), self.doubleSpinBox_right.value(), 0.001)
        plt.plot(x_vals, [polynomial(x) for x in x_vals], label='Newton Polynomial')
        plt.plot(x_vals, [self.f(x) for x in x_vals], label='f(x)')
        plt.plot(x_vals, [abs(self.f(x) - polynomial(x)) for x in x_vals], label='eps(x)')
        plt.xlabel('x')
        plt.ylabel('y')
        plt.title('Newton Polynomial')
        plt.legend()
        plt.grid(True)
        plt.ylim(-1.2, 1.2)
        plt.show()   


    def fill_x_values(self):
        if self.radioButton_random.isChecked():
            return np.random.uniform(self.doubleSpinBox_x_min.value(), self.doubleSpinBox_x_max.value(), self.spinBox_n.value())    
        return [float(x) for x in self.lineEdit_args.text().split()]


    def f(self, x):
        return np.sin(np.pi * np.cos(np.pi * x))


    def create_divided_differences(self, x_vals, y_values, k):
        return sum(y_values[j] / reduce(lambda acc, i: acc * (x_vals[j] - x_vals[i]) if i != j else acc, range(k + 1), 1) for j in range(k + 1))
        

    def create_newton_polynomial(self, x_vals, y_vals):
        divided_differences = [self.create_divided_differences(x_vals, y_vals, i) for i in range(1, len(x_vals))]
        def newton_polynomial(x):
            return y_vals[0] + sum(divided_differences[k - 1] * reduce(lambda acc, j: acc * (x - x_vals[j]), range(k), 1) for k in range(1, len(y_vals)))
        return newton_polynomial


if __name__ == '__main__':
    application = QApplication(sys.argv)
    executable = Lab4()
    executable.show()
    sys.exit(application.exec()) 
    