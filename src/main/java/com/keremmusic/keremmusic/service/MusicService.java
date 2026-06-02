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


    public List<Music> getAllMusics() {
        return musicRepository.findAll();
    }


    public Music getMusicById(Long id) {
        return musicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Şarkı bulunamadı! ID: " + id));
    }


    public Music saveMusic(Music music) {
        return musicRepository.save(music);
    }


    public Music updateMusic(Long id, Music updatedMusic) {
        Music existingMusic = getMusicById(id); // Şarkı var mı diye kontrol ediyoruz

        existingMusic.setTitle(updatedMusic.getTitle());
        existingMusic.setArtist(updatedMusic.getArtist());
        existingMusic.setAlbum(updatedMusic.getAlbum());
        existingMusic.setDuration(updatedMusic.getDuration());

        return musicRepository.save(existingMusic);
    }


    public void deleteMusic(Long id) {
        Music music = getMusicById(id); // Şarkı var mı diye kontrol ediyoruz
        musicRepository.delete(music);
    }
}