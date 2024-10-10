package ru.rsreu.sanitary_ware.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class Item {
    private String productName;
    private Double price;
    private Integer quantity;

}
