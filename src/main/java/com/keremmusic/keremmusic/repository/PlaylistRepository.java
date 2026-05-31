package com.keremmusic.keremmusic.repository;

import com.keremmusic.keremmusic.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByUser_Username(String username);
}
