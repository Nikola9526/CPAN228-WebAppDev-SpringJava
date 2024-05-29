package com.humber.Clothes.Warehouse.App.repositories;

import com.humber.Clothes.Warehouse.App.models.Brand;
import com.humber.Clothes.Warehouse.App.models.Item;

import java.util.*;


public class ItemRepository {
    private static List<Item> items = new ArrayList<>();
    //creating dummy data for the warehouse
    static {
        items.add(
                Item.builder().id(1).name("Summer Dress").brand(Brand.StoneIsland).
                         year_creation(1990)
                        .price(20.00).build()
        );

        items.add(
                Item.builder().id(2).name("Skinny Jeans").brand(Brand.Guess).year_creation(2021)
                        .price(40.00).build()
        );

        items.add(
                Item.builder().id(3).name("Track Suit").brand(Brand.Balenciaga).year_creation(2024)
                        .price(90.00).build()
        );
    }
    //return method
    public static List<Item> getItems() {
        return items;
    }

}
