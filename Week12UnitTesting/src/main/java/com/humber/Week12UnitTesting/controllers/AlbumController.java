package com.humber.Week12UnitTesting.controllers;

import com.humber.Week12UnitTesting.models.Album;
import com.humber.Week12UnitTesting.services.AlbumService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// endpoint for localhost:8080/albums
@RequestMapping("/albums")

public class AlbumController {
    private final AlbumService albumService;
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // get all albums
    @GetMapping("/")
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    // get albums by id
    @GetMapping("/{id}")
    public Album getAlbumById( @PathVariable Integer id) {
       return albumService.getAlbumById(id);
    }

    // save an album
    @PostMapping("/")
    public Album saveAlbum (@RequestBody Album album) {
        return albumService.addAlbum(album);
    }

    //update album
    @PutMapping("/{id}")
    public Album updateAlbum (@PathVariable Integer id, @RequestBody Album album) {
        return albumService.updateAlbum(id, album);
    }

    // delete an album
    @DeleteMapping("/{id}")
    public void deleteAlbum (@PathVariable Integer id) {
        albumService.deleteAlbum(id);
    }
}
