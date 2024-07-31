package com.humber.Week12UnitTesting.services;

import com.humber.Week12UnitTesting.models.Album;
import com.humber.Week12UnitTesting.repositories.AlbumRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
@Service
public class AlbumService {
    // constructor injection
    private final AlbumRepository albumRepository;
    private final RestClient restClient;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
        restClient = RestClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
    }
    // 5 different apis, 5 diff methods

    // get all albums
    public List<Album> getAllAlbums() {
        // gets all albums from JSON api  from Web
        return restClient.
                get()
                // get method
                .uri("/albums")
                .retrieve()
                .body(
                        new ParameterizedTypeReference<List<Album>>() {
                        }
                );
    }

    // get album by id  // gets albums for specific ID
    // GET
    public Album getAlbumById(int id) {
        return restClient
                .get()
                // get method
                .uri("/albums/{id}", id)
                .retrieve()
                .body(Album.class);
    }
    // save an album
    // POST
    public Album addAlbum(Album album) {
        //return albumRepository.save(album);
        return restClient
                .post()
                .uri("/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .body(album)
                .retrieve()
                .body(Album.class);
    }
    // update album
    //PUT
    public Album updateAlbum(Integer id, Album album) {
        return restClient
                .post()
                .uri("/albums/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(album)
                .retrieve()
                .body(Album.class);
    }
    // delete album
    // DELETE
    public void deleteAlbum(Integer id) {
        // albumRepository.deleteById(id);
        restClient
                .delete()
                .uri("/albums/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}