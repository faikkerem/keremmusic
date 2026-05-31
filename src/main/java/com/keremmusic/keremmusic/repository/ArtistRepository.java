package com.keremmusic.keremmusic.repository;

import com.keremmusic.keremmusic.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByNameIgnoreCase(String name);
}
