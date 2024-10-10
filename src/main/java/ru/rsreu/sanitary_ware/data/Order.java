package ru.rsreu.sanitary_ware.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.rsreu.sanitary_ware.dto.ItemDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Getter @Setter
public class Order {
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private ItemDTO item;
    private Double totalPrice;
}
