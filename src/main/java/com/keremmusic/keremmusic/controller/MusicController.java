package com.keremmusic.keremmusic.controller;

import com.keremmusic.keremmusic.entity.Music;
import com.keremmusic.keremmusic.service.MusicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/musics")
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    // 1. Tüm Şarkıları Listeleme API Ucu
    @GetMapping
    public ResponseEntity<List<Music>> getAllMusics() {
        return ResponseEntity.ok(musicService.getAllMusics());
    }

    // 2. ID'ye Göre Şarkı Getirme API Ucu
    @GetMapping("/{id}")
    public ResponseEntity<?> getMusicById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(musicService.getMusicById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 3. Yeni Şarkı Ekleme API Ucu
    @PostMapping
    public ResponseEntity<?> saveMusic(@Valid @RequestBody Music music) {
        try {
            return ResponseEntity.ok(musicService.saveMusic(music));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 4. Şarkı Güncelleme API Ucu
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMusic(@PathVariable Long id, @Valid @RequestBody Music music) {
        try {
            return ResponseEntity.ok(musicService.updateMusic(id, music));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 5. Şarkı Silme API Ucu
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMusic(@PathVariable Long id) {
        try {
            musicService.deleteMusic(id);
            return ResponseEntity.ok("Şarkı başarıyla silindi. ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}