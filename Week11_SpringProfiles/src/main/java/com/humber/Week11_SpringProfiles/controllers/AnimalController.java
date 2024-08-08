package com.humber.Week11_SpringProfiles.controllers;

import com.humber.Week11_SpringProfiles.configs.DBConfig;
import com.humber.Week11_SpringProfiles.configs.DefaultDBConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animal")

public class AnimalController {
    // constructor injection
    //private final DefaultDBConfig defaultDBConfig;
    private final DBConfig dbConfig; // this is just the interface that implements in 3 classes

    public AnimalController(DBConfig  dbConfig) { // more than one implementation of it there are 3
        // use spring profiles to fix
        //this.defaultDBConfig = defaultDBConfig;
        this.dbConfig =  dbConfig;
    }

    @GetMapping
    public void getAnimal() {
       dbConfig.setupDBConnection();
    }
}