package com.humber.Week10RestTemplate_Album.services;

import com.humber.Week10RestTemplate_Album.models.Album;
import com.humber.Week10RestTemplate_Album.repostories.AlbumRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final RestTemplate restTemplate;

    //constructor injection injecting RestTemplate + Album Repo into Album Service
    public AlbumService(AlbumRepository albumRepository, RestTemplate restTemplate) {
        this.albumRepository = albumRepository;
        this.restTemplate = restTemplate;
    }
    // API URL variables
        // external one to fetch albums from JSON From Web
        // internal one to send the fetched data to dashboard where it gets posted and saved to DB //8081
    private final String EXTERNAL_API_URL = "https://jsonplaceholder.typicode.com";
    private final String INTERNAL_API_URL = "http://localhost:8081/dashboard";

    // endpoints ()
    // get call to external API and post call to internal api
    // get Albums /*List<Album> albums*/
    public List<Album> getAllAlbums() {

       // get albums data from 3rd party api using Rest Template
        // 4 parameters, 4 agruments to pass
        //1 is external api url
        // 2 is http method (its GET)
        // 3 is null
        // 4 is type of data it is getting

        ResponseEntity<List<Album>> response = restTemplate.exchange(EXTERNAL_API_URL + "/albums",
                HttpMethod.GET,
                null, // null 3rd parameter no headers or other configurations
                new ParameterizedTypeReference<List<Album>>() {
                }); // stores in response

        // save to the response data to the database (8080 DB) // saves in Microservice 1 DB
        albumRepository.saveAll(response.getBody());

        // post the data to the internal api (dashboard) and save its  (8081 DB)
        sendAlbumsToDashboard(response.getBody());
        return response.getBody();
    }
    // utility method to send the data to internal api
    private void sendAlbumsToDashboard(List<Album> albums) {
        ResponseEntity<String> response = restTemplate.
                postForEntity(INTERNAL_API_URL + "/save",
                        albums,
                        String.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Data Sent to the Internal API successfully!");
        }
    }
}