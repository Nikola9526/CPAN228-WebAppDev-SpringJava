package com.humber.Week10RestClient.repositories;

import com.humber.Week10RestClient.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
