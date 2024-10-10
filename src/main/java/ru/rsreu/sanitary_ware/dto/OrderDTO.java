package ru.rsreu.sanitary_ware.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter
@RequiredArgsConstructor
public class OrderDTO {
    @NotEmpty(message = "Имя клиента не должно быть пустым")
    @Size(min = 2, max = 50, message = "Имя клиента должно быть от 2 до 50 символов")
    private String customerName;

    @NotEmpty(message = "Номер телефона не должен быть пустым")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Неверный формат номера телефона")
    private String customerPhone;

    @Email(message = "Неверный формат электронной почты")
    private String customerEmail;

    @NotNull(message = "Список товаров не может быть пустым")
    private ItemDTO item;

    @NotNull(message = "Общая сумма заказа не может быть пустой")
    private Double totalPrice;
}
