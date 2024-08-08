package com.humber.Week10RestTemplate_Album.repostories;

import com.humber.Week10RestTemplate_Album.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
