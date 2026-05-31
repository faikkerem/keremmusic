package com.keremmusic.keremmusic.controller;

import com.keremmusic.keremmusic.dto.AlbumRequestDto;
import com.keremmusic.keremmusic.entity.Album;
import com.keremmusic.keremmusic.service.AlbumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Album> createAlbum(@Valid @RequestBody AlbumRequestDto request) {
        return ResponseEntity.ok(albumService.createAlbum(request));
    }
}
