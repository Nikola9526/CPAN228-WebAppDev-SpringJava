package com.humber.Clothes.Warehouse.App.models;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Item {
    private int id;
    private String name;
    private Brand brand;
    private int year_creation;
    private double price;
}
// String test =  brand.StoneIsland