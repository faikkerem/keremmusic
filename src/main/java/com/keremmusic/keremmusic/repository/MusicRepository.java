package com.keremmusic.keremmusic.repository;

import com.keremmusic.keremmusic.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    // Şarkıları listelemek, eklemek, silmek ve güncellemek için gerekli tüm metotlar JpaRepository ile otomatik geliyor.
}