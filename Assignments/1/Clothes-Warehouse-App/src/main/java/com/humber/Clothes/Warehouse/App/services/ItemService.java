package com.humber.Clothes.Warehouse.App.services;

import com.humber.Clothes.Warehouse.App.models.Item;
import com.humber.Clothes.Warehouse.App.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ItemService {
    //return item repo class
    public List<Item> getAllItems(){
        return ItemRepository.getItems();
    }
}