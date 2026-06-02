package com.keremmusic.keremmusic.controller;

import com.keremmusic.keremmusic.entity.Artist;
import com.keremmusic.keremmusic.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistRepository artistRepository;

    @PostMapping("/add")
    public Artist addArtist(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }

    @GetMapping
    public List<Artist> getAll() {
        return artistRepository.findAll();
    }
}