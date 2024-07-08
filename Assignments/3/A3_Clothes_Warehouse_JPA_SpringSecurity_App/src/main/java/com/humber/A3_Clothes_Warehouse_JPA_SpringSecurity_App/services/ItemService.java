package com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.services;

import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.Brand;
import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.Item;
import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    // constructor Injection
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // save Item
    public int saveItem(Item item){
        if (item.getPrice() > 1000) {
            return 0; //fail
        } else if (item.getYear_creation() > 2024) {
            return 0; // fail
        }
        //else
        itemRepository.save(item);
        return 1; // success
    }

    //deletes an item (clothes from online inventory)
    public int deleteItem(int id){
        if(itemRepository.existsById(id)){
            // if the id is found in Repo, DB godhead with delete
            itemRepository.deleteById(id);
            return 1; // success
        } //else
        return 0; // fail, error
    }

    // updates item
    public int updateItem(Item item){
        if (item.getPrice() > 1000) {
            return 0; // fail
        } else if ( item.getYear_creation() >2024) {
            return 0; // fail
        }//else
        itemRepository.save(item);
        return 1; // success updates item
    }
    // get an item by ID
    public Optional getItemById(int id){
        return itemRepository.findById(id);
    }

    // pagination and Sorting Method //getAll
    public Page<Item> getPaginatedItems(int pageSize, int pageNo, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();


        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return itemRepository.findAll(pageable);
        //returns page of items (clothes)
    }
    //pagination method, only filtered items
    public Page<Item> getFilteredPaginatedItems(int pageSize, int pageNo, Brand searchBrand, Double searchPrice) {

        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        if (searchBrand != null && searchPrice != null) {
            return itemRepository.findByBrandAndPrice(searchBrand, searchPrice ,pageable);
        } else if (searchPrice != null) {
            return itemRepository.findByPrice(searchPrice, pageable);
        } //else searchBrand != null only
        return itemRepository.findByBrand(searchBrand, pageable);
    }
}