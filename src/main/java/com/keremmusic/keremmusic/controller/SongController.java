package com.keremmusic.keremmusic.controller;

import com.keremmusic.keremmusic.dto.SongRequestDto;
import com.keremmusic.keremmusic.dto.SongResponseDto;
import com.keremmusic.keremmusic.service.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping
    public ResponseEntity<List<SongResponseDto>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponseDto> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @GetMapping("/popular")
    public ResponseEntity<List<SongResponseDto>> getPopularSongs() {
        return ResponseEntity.ok(songService.getTop10PopularSongs());
    }

    @GetMapping("/by-artist")
    public ResponseEntity<List<SongResponseDto>> getByArtist(@RequestParam String artist) {
        return ResponseEntity.ok(songService.getSongsByArtist(artist));
    }

    @GetMapping("/by-genre")
    public ResponseEntity<List<SongResponseDto>> getByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(songService.getSongsByGenre(genre));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SongResponseDto> createSong(@Valid @RequestBody SongRequestDto request) {
        return ResponseEntity.ok(songService.createSong(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SongResponseDto> updateSong(@PathVariable Long id, @Valid @RequestBody SongRequestDto request) {
        return ResponseEntity.ok(songService.updateSong(id, request));
    }

    @DeleteMapping("/{id}")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.ok("Şarkı silindi. ID: " + id);
    }
}
