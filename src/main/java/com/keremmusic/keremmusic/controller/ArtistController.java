package com.keremmusic.keremmusic.controller;

import com.keremmusic.keremmusic.dto.ArtistRequestDto;
import com.keremmusic.keremmusic.entity.Artist;
import com.keremmusic.keremmusic.service.ArtistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Artist> createArtist(@Valid @RequestBody ArtistRequestDto request) {
        return ResponseEntity.ok(artistService.createArtist(request));
    }
}
