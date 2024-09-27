package ru.rsreu.lab1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
@RequiredArgsConstructor
public class ItemDTO {
    @NotEmpty(message = "Название товара не должно быть пустым")
    private String productName;

    @NotNull(message = "Цена товара не должна быть пустой")
    @Min(value = 0, message = "Цена товара не может быть отрицательной")
    private Double price;

    @NotNull(message = "Количество товара не может быть пустым")
    @Min(value = 1, message = "Количество товара должно быть больше нуля")
    private Integer quantity;
}
