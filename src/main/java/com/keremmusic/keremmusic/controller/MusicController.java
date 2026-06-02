package com.keremmusic.keremmusic.controller;

import com.keremmusic.keremmusic.entity.Music;
import com.keremmusic.keremmusic.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/musics")
@RequiredArgsConstructor
public class MusicController {

    private final MusicRepository musicRepository;

    @PostMapping("/add")
    public Music addMusic(@RequestBody Music music) {
        System.out.println("Gelen Müzik Verisi: " + music.getTitle());
        Music savedMusic = musicRepository.save(music);
        System.out.println("Veritabanına kaydedildi, ID: " + savedMusic.getId());
        return savedMusic;
    }


    @GetMapping
    public List<Music> getAll() {
        return musicRepository.findAll();

    }
}