package ru.rsreu.sanitary_ware.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "contact_person")
    private String contactPerson;

    @Size(max = 20)
    @Column(name = "contact_phone", length = 20)
    private String contactPhone;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

}