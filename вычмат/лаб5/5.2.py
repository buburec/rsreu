import numpy as np
import matplotlib.pyplot as plt


def f(x, y1, y2):
    return np.arctan(x**2 + y2**2)


def g(x, y1, y2):
    return np.sin(x + y1)


def runge_kutta4(x0, y1_0, y2_0, xf, h):
    n = int((xf - x0) / h)
    x = np.linspace(x0, xf, n+1)
    y1 = np.zeros(n+1)
    y2 = np.zeros(n+1)
    y1[0] = y1_0
    y2[0] = y2_0
    
    for i in range(n):
        k0 = f(x[i], y1[i], y2[i])
        l0 = g(x[i], y1[i], y2[i])
        k1 = f(x[i] + (h * k0) / 2, y1[i] + (h * l0) / 2, y2[i] + h / 2)
        l1 = g(x[i] + (h * k0) / 2, y1[i] + (h * l0) / 2, y2[i] + h / 2)
        k2 = f(x[i] + (h * k1) / 2, y1[i] + (h * l1) / 2, y2[i] + h / 2)
        l2 = g(x[i] + (h * k1) / 2, y1[i] + (h * l1) / 2, y2[i] + h / 2)
        k3 = f(x[i] + h * k2, y1[i] + h * l2, y2[i] + h)
        l3 = g(x[i] + h * k2, y1[i] + h * l2, y2[i] + h)
        
        y1[i+1] = y1[i] + h * (k0 + 2*k1 + 2*k2 + k3) / 6
        y2[i+1] = y2[i] + h * (l0 + 2*l1 + 2*l2 + l3) / 6
    
    return y1, y2


a = 0
y1_0 = 0.5
y2_0 = 1.5
b = 2
h = 0.1

y1, y2 = runge_kutta4(a, y1_0, y2_0, b, h)

# plt.plot(np.arange(a, b + h, h), y1, label='rk4')
# plt.plot(np.arange(a, b + h, h), y2, label='rk4')
plt.plot(y1, y2, label='rk4')
plt.title('Solution using Runge-Kutta Method')
plt.xlabel('x')
plt.ylabel('y(x)')
plt.legend()
plt.grid(True)
plt.show()
