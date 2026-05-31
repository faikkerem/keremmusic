package com.keremmusic.keremmusic.service;

import com.keremmusic.keremmusic.dto.ArtistRequestDto;
import com.keremmusic.keremmusic.entity.Artist;
import com.keremmusic.keremmusic.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Transactional
    public Artist createArtist(ArtistRequestDto request) {
        if (artistRepository.findByNameIgnoreCase(request.getName()).isPresent()) {
            throw new RuntimeException("Bu isimde bir sanatçı zaten mevcut.");
        }

        Artist artist = new Artist();
        artist.setName(request.getName());
        artist.setCountry(request.getCountry());
        artist.setBiography(request.getBiography());

        return artistRepository.save(artist);
    }
}
