package ru.rsreu.lab1.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Getter @Setter
public class Item {
    private String productName;
    private Double price;
    private Integer quantity;

}
