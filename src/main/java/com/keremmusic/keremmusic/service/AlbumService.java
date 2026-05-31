package com.keremmusic.keremmusic.service;

import com.keremmusic.keremmusic.dto.AlbumRequestDto;
import com.keremmusic.keremmusic.entity.Album;
import com.keremmusic.keremmusic.entity.Artist;
import com.keremmusic.keremmusic.repository.AlbumRepository;
import com.keremmusic.keremmusic.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Transactional
    public Album createAlbum(AlbumRequestDto request) {
        Artist artist = artistRepository.findById(request.getArtistId())
                .orElseThrow(() -> new RuntimeException("Sanatçı bulunamadı. ID: " + request.getArtistId()));

        Album album = new Album();
        album.setTitle(request.getTitle());
        album.setArtist(artist);
        album.setReleaseDate(request.getReleaseDate());
        album.setCoverUrl(request.getCoverUrl());

        return albumRepository.save(album);
    }
}
