package ru.rsreu.lab1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
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
    private List<ItemDTO> items;

    @NotNull(message = "Общая сумма заказа не может быть пустой")
    private Double totalPrice;
}
