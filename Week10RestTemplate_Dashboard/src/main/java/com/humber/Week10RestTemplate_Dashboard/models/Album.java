package com.humber.Week10RestTemplate_Dashboard.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Album {
    //fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // responsible for auto increment
    private Integer id;
    private String title;
    private Integer userId;

}
