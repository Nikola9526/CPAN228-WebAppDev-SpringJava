package com.humber.Week12UnitTesting.repositories;

import com.humber.Week12UnitTesting.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
