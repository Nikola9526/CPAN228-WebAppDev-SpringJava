package com.humber.Week12AUnitTesting.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Cuisine Class is ready to be embedded in another class
@Embeddable
//no need for @Enitiy because part of Dish table
public class Cuisine {
    private String cuisineName;
    private String country;
}
