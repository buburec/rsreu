@withdraval
Feature: Удаление товара

  Scenario: Удаление товара
    Given Товар, который пользователь хочет удалить
    When Пользователь нажимает кнопку "Удалить"
    Then Товар удаляется из корзины