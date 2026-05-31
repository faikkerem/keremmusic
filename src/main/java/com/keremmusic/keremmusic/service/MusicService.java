package com.keremmusic.keremmusic.service;

import com.keremmusic.keremmusic.entity.Music;
import com.keremmusic.keremmusic.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;

    // 1. Tüm Şarkıları Listeleme
    public List<Music> getAllMusics() {
        return musicRepository.findAll();
    }

    // 2. ID'ye Göre Şarkı Bulma
    public Music getMusicById(Long id) {
        return musicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Şarkı bulunamadı! ID: " + id));
    }

    // 3. Yeni Şarkı Ekleme
    public Music saveMusic(Music music) {
        return musicRepository.save(music);
    }

    // 4. Şarkı Güncelleme
    public Music updateMusic(Long id, Music updatedMusic) {
        Music existingMusic = getMusicById(id); // Şarkı var mı diye kontrol ediyoruz

        existingMusic.setTitle(updatedMusic.getTitle());
        existingMusic.setArtist(updatedMusic.getArtist());
        existingMusic.setAlbum(updatedMusic.getAlbum());
        existingMusic.setDuration(updatedMusic.getDuration());

        return musicRepository.save(existingMusic);
    }

    // 5. Şarkı Silme
    public void deleteMusic(Long id) {
        Music music = getMusicById(id); // Şarkı var mı diye kontrol ediyoruz
        musicRepository.delete(music);
    }
}