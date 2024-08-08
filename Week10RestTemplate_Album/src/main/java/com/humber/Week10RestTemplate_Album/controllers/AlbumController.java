package com.humber.Week10RestTemplate_Album.controllers;

import com.humber.Week10RestTemplate_Album.models.Album;
import com.humber.Week10RestTemplate_Album.services.AlbumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
// end point for Microservice 1 Album localhost:8080/albums
@RequestMapping("/albums")

public class AlbumController {
    private final AlbumService albumService;

    //constructor injection
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // get all albums from JSON Web and save them to the database (its own )and call internal API (and save there),
    // returns fetched data to Post man
    @GetMapping("/all")
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
        // check if postman has data
        // check h2 has data
    }
}