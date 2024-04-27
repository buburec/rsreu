import torch
import torch.nn as nn
import torch.optim as optim
import numpy as np
import matplotlib.pyplot as plt

# Создание набора данных
def generate_data(num_samples):
    X = np.random.rand(num_samples, 1) # Генерация случайных значений X
    Y = 2*X + 1 + np.random.randn(num_samples, 1)*0.1 # Генерация линейной зависимости с шумом
    return X, Y

# Построение нейронной сети
class LinearRegression(nn.Module):
    def __init__(self):
        super(LinearRegression, self).__init__()
        self.fc = nn.Linear(1, 1) # Один входной узел и один выходной узел

    def forward(self, x):
        return self.fc(x)

# Обучение модели
def train_model(model, criterion, optimizer, X_train, Y_train, num_epochs):
    for epoch in range(num_epochs):
        inputs = torch.from_numpy(X_train).float()
        labels = torch.from_numpy(Y_train).float()

        optimizer.zero_grad()
        outputs = model(inputs)
        loss = criterion(outputs, labels)
        loss.backward()
        optimizer.step()

        if (epoch+1) % 100 == 0:
            print('Epoch [{}/{}], Loss: {:.4f}'.format(epoch+1, num_epochs, loss.item()))

# Подготовка данных
X_train, Y_train = generate_data(100)

# Инициализация модели, функции потерь и оптимизатора
model = LinearRegression()
criterion = nn.MSELoss()
optimizer = optim.SGD(model.parameters(), lr=0.01)

# Обучение модели
train_model(model, criterion, optimizer, X_train, Y_train, num_epochs=1000)

# Визуализация результата
predicted = model(torch.from_numpy(X_train).float()).detach().numpy()
plt.scatter(X_train, Y_train, label='Original data')
plt.plot(X_train, predicted, label='Fitted line', color='red')
plt.legend()
plt.show()
