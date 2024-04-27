import numpy as np
import matplotlib.pyplot as plt


def rk4(x0, xn, y0, h, f):
    y = y0
    x = x0
    n = int((xn - x0) / h)
    y_vector = [y]
    
    for i in range(n):
        k1 = h * f(x, y)
        k2 = h * f(x + h/2, y + k1/2)
        k3 = h * f(x + h/2, y + k2/2)
        k4 = h * f(x + h, y + k3)
        x += h
        y += (k1 + 2*k2 + 2*k3 + k4)/6
        y_vector.append(y)
    
    return np.array(y_vector)


def f(x, y):
    return -y * np.log(y) / x

x0 = 1
y0 = np.e
xf = 2.6
h = 0.1

y = rk4(x0, xf, y0, h, f)

plt.plot(np.arange(x0, xf + h, h), y, label='rk4')
plt.xlabel('x')
plt.ylabel('y')
plt.legend()
plt.grid(True)
plt.show()