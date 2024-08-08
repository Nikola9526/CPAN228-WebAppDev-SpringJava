package com.humber.Week10RestTemplate_Dashboard.services;

import com.humber.Week10RestTemplate_Dashboard.models.Album;
import com.humber.Week10RestTemplate_Dashboard.repositories.DashboardRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DashboardService {
    //constructor injection (injecting Dashboard Repo class)
    private final DashboardRepository dashboardRepository;
    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    // save all albums from microservice 1 that it gets it POSTs here in its own 8081 h2 DB
    public void saveAllAlbum(List<Album> albums) {
        dashboardRepository.saveAll(albums);
    }
}
