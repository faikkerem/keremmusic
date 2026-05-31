package com.keremmusic.keremmusic.service;

import com.keremmusic.keremmusic.dto.SongRequestDto;
import com.keremmusic.keremmusic.dto.SongResponseDto;
import com.keremmusic.keremmusic.entity.Album;
import com.keremmusic.keremmusic.entity.Artist;
import com.keremmusic.keremmusic.entity.Genre;
import com.keremmusic.keremmusic.entity.Song;
import com.keremmusic.keremmusic.exception.ResourceNotFoundException;
import com.keremmusic.keremmusic.repository.AlbumRepository;
import com.keremmusic.keremmusic.repository.ArtistRepository;
import com.keremmusic.keremmusic.repository.GenreRepository;
import com.keremmusic.keremmusic.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;

    public List<SongResponseDto> getAllSongs() {
        return songRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public SongResponseDto getSongById(Long id) {
        return songRepository.findById(id).map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Şarkı bulunamadı. ID: " + id));
    }

    public List<SongResponseDto> getTop10PopularSongs() {
        return songRepository.findTop10ByOrderByPlayCountDesc().stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<SongResponseDto> getSongsByArtist(String artistName) {
        return songRepository.findByArtist_NameIgnoreCase(artistName).stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<SongResponseDto> getSongsByGenre(String genreName) {
        return songRepository.findByGenre_NameIgnoreCase(genreName).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public SongResponseDto createSong(SongRequestDto request) {
        Artist artist = artistRepository.findById(request.getArtistId())
                .orElseThrow(() -> new ResourceNotFoundException("Sanatçı bulunamadı. ID: " + request.getArtistId()));

        Album album = null;
        if (request.getAlbumId() != null) {
            album = albumRepository.findById(request.getAlbumId())
                    .orElseThrow(() -> new ResourceNotFoundException("Albüm bulunamadı. ID: " + request.getAlbumId()));
        }

        Genre genre = null;
        if (request.getGenreId() != null) {
            genre = genreRepository.findById(request.getGenreId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tür bulunamadı. ID: " + request.getGenreId()));
        }

        Song song = new Song();
        song.setTitle(request.getTitle());
        song.setArtist(artist);
        song.setAlbum(album);
        song.setGenre(genre);
        song.setDurationSeconds(request.getDurationSeconds());
        song.setUrl(request.getUrl());
        song.setPlayCount(0L);

        return toDto(songRepository.save(song));
    }

    @Transactional
    public SongResponseDto updateSong(Long id, SongRequestDto request) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Şarkı bulunamadı. ID: " + id));

        Artist artist = artistRepository.findById(request.getArtistId())
                .orElseThrow(() -> new ResourceNotFoundException("Sanatçı bulunamadı. ID: " + request.getArtistId()));

        Album album = null;
        if (request.getAlbumId() != null) {
            album = albumRepository.findById(request.getAlbumId())
                    .orElseThrow(() -> new ResourceNotFoundException("Albüm bulunamadı. ID: " + request.getAlbumId()));
        }

        Genre genre = null;
        if (request.getGenreId() != null) {
            genre = genreRepository.findById(request.getGenreId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tür bulunamadı. ID: " + request.getGenreId()));
        }

        song.setTitle(request.getTitle());
        song.setArtist(artist);
        song.setAlbum(album);
        song.setGenre(genre);
        song.setDurationSeconds(request.getDurationSeconds());
        song.setUrl(request.getUrl());

        return toDto(songRepository.save(song));
    }

    @Transactional
    public void deleteSong(Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Şarkı bulunamadı. ID: " + id));
        songRepository.delete(song);
    }

    private SongResponseDto toDto(Song song) {
        return new SongResponseDto(
                song.getId(),
                song.getTitle(),
                song.getArtist().getName(),
                song.getAlbum() != null ? song.getAlbum().getTitle() : null,
                song.getGenre() != null ? song.getGenre().getName() : null,
                song.getDurationSeconds(),
                song.getUrl(),
                song.getPlayCount()
        );
    }
}