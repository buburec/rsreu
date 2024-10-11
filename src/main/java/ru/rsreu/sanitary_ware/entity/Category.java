package ru.rsreu.sanitary_ware.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

}