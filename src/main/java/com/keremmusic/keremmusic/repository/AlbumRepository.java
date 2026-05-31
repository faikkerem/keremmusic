package com.keremmusic.keremmusic.repository;

import com.keremmusic.keremmusic.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findByTitleIgnoreCase(String title);
}
