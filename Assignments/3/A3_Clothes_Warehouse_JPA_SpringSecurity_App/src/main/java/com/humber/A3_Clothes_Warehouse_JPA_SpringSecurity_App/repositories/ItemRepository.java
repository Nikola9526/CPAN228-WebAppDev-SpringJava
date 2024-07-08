package com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.repositories;

import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.Brand;
import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    //custom JPA queries to Filter Results By
    Page<Item> findByBrand(Brand brand, Pageable pageable);
    Page<Item> findByBrandAndPrice(Brand brand, double price, Pageable pageable);
    Page <Item> findByPrice(double price, Pageable pageable);

}
