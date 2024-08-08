package com.humber.Week10RestTemplate_Dashboard.repositories;

import com.humber.Week10RestTemplate_Dashboard.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

// Repo extending JpaRepository for DB interactions
public interface DashboardRepository extends JpaRepository<Album, Integer> {

}
