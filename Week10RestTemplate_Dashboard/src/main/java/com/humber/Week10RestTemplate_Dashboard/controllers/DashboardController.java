package com.humber.Week10RestTemplate_Dashboard.controllers;

import com.humber.Week10RestTemplate_Dashboard.models.Album;
import com.humber.Week10RestTemplate_Dashboard.services.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// endpoint for 8081 dashboard microservice 2
@RequestMapping("/dashboard")
public class DashboardController {

    //constructor injection
    private final DashboardService dashboardService;
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }
    //save all albums
    // POST /dashboard/save
    @PostMapping("/save")
    public void saveAllAlbums(@RequestBody List<Album> albums) {
        dashboardService.saveAllAlbum(albums);
        System.out.println("Album saved Successfully, in Dashboard Microservice!");
    }
}
