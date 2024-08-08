package com.humber.Week10RestTemplate_Album.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Album {
    //fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // responsible for auto increment
    private Integer id;
    private String title;
    private Integer userId;
}
