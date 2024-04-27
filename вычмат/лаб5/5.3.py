import sympy as sp
import matplotlib.pyplot as plt
import numpy as np
from math import *

t = sp.symbols('t')
y = sp.Function('y')
equation = sp.Eq(y(t).diff(t, t) - 4 * y(t).diff(t) + 4 * y(t), 0)
solution = sp.dsolve(equation)

C1 = 0
C2 = 1
x_vals = np.arange(-3, 0.5, 0.01)
plt.plot(x_vals, [eval(str(solution.rhs)) for t in x_vals], label='rk4')
plt.xlabel('x')
plt.ylabel('y')
plt.legend()
plt.grid(True)
plt.show()
