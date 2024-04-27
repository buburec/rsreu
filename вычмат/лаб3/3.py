import sys
import os
from PyQt6 import uic
from PyQt6.QtWidgets import QApplication, QMainWindow, QDoubleSpinBox
import numpy as np


class Lab3(QMainWindow):
    def __init__(self):
        super().__init__()
        uic.loadUi('main.ui', self)
        self.pushButton_calculate.clicked.connect(self.calculate_matrix)
        self.pushButton_clear.clicked.connect(self.clear_matrix)
        self.pushButton_default.clicked.connect(self.default_matrix)


    def create_matrix(self):
        return np.array([[self.doubleSpinBox_00.value(), self.doubleSpinBox_01.value(), self.doubleSpinBox_02.value(), self.doubleSpinBox_03.value()], 
                        [self.doubleSpinBox_10.value(), self.doubleSpinBox_11.value(), self.doubleSpinBox_12.value(), self.doubleSpinBox_13.value()], 
                        [self.doubleSpinBox_20.value(), self.doubleSpinBox_21.value(), self.doubleSpinBox_22.value(), self.doubleSpinBox_23.value()], 
                        [self.doubleSpinBox_30.value(), self.doubleSpinBox_31.value(), self.doubleSpinBox_32.value(), self.doubleSpinBox_33.value()]])
        

    def create_free_coefs(self):
        return np.array([[self.doubleSpinBox_04.value()],
                        [self.doubleSpinBox_14.value()],
                        [self.doubleSpinBox_24.value()],
                        [self.doubleSpinBox_34.value()]])


    def alpha_coef(self, A, length):
        alpha = np.array([[0.0 for _ in range(length)] for _ in range(length)])
        for i in range(length):
            for j in range(length):
                if j != i:
                    alpha[i, j] = -A[i, j] / A[i, i]
        return alpha
    

    def beta_coef(self, A, B, length):
        beta = np.array([[0.0] for _ in range(length)])
        for i in range(length):
            beta[i] = B[i] / A[i, i]
        return beta
    

    def iterations_method(self, alpha, beta, eps=1e-4, max_iterations=1000):
        x = np.zeros_like(beta)
        x_prev = x.copy()
        for _ in range(max_iterations):
            for i in range(len(x)):
                summary = 0
                for j in range(len(x)):
                    if i != j:
                        summary += alpha[i, j] * x[j]
                x[i] = summary + beta[i]
            if np.abs(x - x_prev).all() < eps:
                return x
            x_prev = x.copy()
        return x


    def make_diagonally_dominant(self, matrix):
        n = matrix.shape[0]
        for i in range(n):
            max_index = i
            max_val = abs(matrix[i][i])
            for j in range(n):
                if abs(matrix[j][i]) > max_val:
                    max_val = abs(matrix[j][i])
                    max_index = j
            if max_index != i:
                matrix[[i, max_index]] = matrix[[max_index, i]]
        return matrix
    

    def lu_decomposition(self, matrix):
        n = len(matrix)
        L = np.eye(n)
        U = np.zeros((n, n))
        for i in range(n):
            for k in range(i, n):
                U[i][k] = matrix[i][k] - np.dot(L[i,:i], U[:i,k])
            for k in range(i + 1, n):
                L[k][i] = (matrix[k][i] - np.dot(L[k,:i], U[:i,i])) / U[i][i]
        return U


    def determinant(self, matrix):
        U = self.lu_decomposition(matrix)
        det = np.prod(np.diag(U))
        return det


    def condition(self, matrix):
        inv_matrix = np.linalg.inv(matrix)
        return np.max(np.sum(np.abs(matrix), axis=1)) * np.max(np.sum(np.abs(inv_matrix), axis=1))


    def calculate_matrix(self):
        A = self.create_matrix()
        B = self.create_free_coefs()
        merged_matrix = self.make_diagonally_dominant(np.hstack((A, B[:, 0:1])))
        A_NEW, B_NEW = merged_matrix[:, :len(B)], merged_matrix[:, len(B):]
        print(A)
        print(A_NEW)
        alpha_matrix = self.alpha_coef(A_NEW, len(B))
        beta_matrix = self.beta_coef(A_NEW, B_NEW, len(B))
        x = self.iterations_method(alpha_matrix, beta_matrix)
        self.label_x1.setText(str(*x[0]))
        self.label_x2.setText(str(*x[1]))
        self.label_x3.setText(str(*x[2]))
        self.label_x4.setText(str(*x[3]))
        self.label_A.setText(str(self.determinant(A)))
        self.label_cond_A.setText(str(self.condition(A)))


    def clear_matrix(self):
        self.label_x1.setText("...")
        self.label_x2.setText("...")
        self.label_x3.setText("...")
        self.label_x4.setText("...")
        self.label_A.setText("...")
        self.label_cond_A.setText("...")
        for obj in self.findChildren(QDoubleSpinBox):
            obj.setValue(0.0)   


    def default_matrix(self):
        python = sys.executable
        os.execl(python, python, *sys.argv)


if __name__ == '__main__':
    application = QApplication(sys.argv)
    executable = Lab3()
    executable.show()
    sys.exit(application.exec())