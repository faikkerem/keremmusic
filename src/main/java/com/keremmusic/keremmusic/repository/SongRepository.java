package com.keremmusic.keremmusic.repository;

import com.keremmusic.keremmusic.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findTop10ByOrderByPlayCountDesc();
    List<Song> findByArtist_NameIgnoreCase(String artistName);
    List<Song> findByGenre_NameIgnoreCase(String genreName);
    List<Song> findByAlbum_TitleIgnoreCase(String albumTitle);
    List<Song> findByTitleContainingIgnoreCase(String title);
}
