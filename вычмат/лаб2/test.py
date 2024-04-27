from sympy import symbols, diff

# Определяем символ для переменной
x = symbols('x')

# Определяем функцию
f_x = "3 * x**2 + 2*x + 1"

# Вычисляем производную функции по переменной x
derivative = diff(f_x, x)

# Выводим результат
print("Производная функции:", type(derivative))
print(derivative.subs(x, 2))
