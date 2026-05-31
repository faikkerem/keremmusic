package com.keremmusic.keremmusic.service;

import com.keremmusic.keremmusic.dto.PlaylistRequestDto;
import com.keremmusic.keremmusic.dto.PlaylistResponseDto;
import com.keremmusic.keremmusic.dto.SongResponseDto;
import com.keremmusic.keremmusic.entity.Playlist;
import com.keremmusic.keremmusic.entity.Song;
import com.keremmusic.keremmusic.entity.User;
import com.keremmusic.keremmusic.repository.PlaylistRepository;
import com.keremmusic.keremmusic.repository.SongRepository;
import com.keremmusic.keremmusic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public List<PlaylistResponseDto> getPlaylistsForUser(String username) {
        return playlistRepository.findByUser_Username(username).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public PlaylistResponseDto createPlaylist(String username, PlaylistRequestDto request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + username));

        Playlist playlist = new Playlist();
        playlist.setName(request.getName());
        playlist.setUser(user);
        playlist.setSongs(resolveSongs(request.getSongIds()));

        return toDto(playlistRepository.save(playlist));
    }

    @Transactional
    public PlaylistResponseDto addSongToPlaylist(Long playlistId, Long songId, String username) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist bulunamadı. ID: " + playlistId));
        authorizeUser(playlist, username);

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Şarkı bulunamadı. ID: " + songId));

        if (!playlist.getSongs().contains(song)) {
            playlist.getSongs().add(song);
        }
        return toDto(playlistRepository.save(playlist));
    }

    @Transactional
    public PlaylistResponseDto removeSongFromPlaylist(Long playlistId, Long songId, String username) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist bulunamadı. ID: " + playlistId));
        authorizeUser(playlist, username);

        playlist.getSongs().removeIf(song -> song.getId().equals(songId));
        return toDto(playlistRepository.save(playlist));
    }

    private void authorizeUser(Playlist playlist, String username) {
        if (!playlist.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Bu işlem için yetkiniz yok.");
        }
    }

    private PlaylistResponseDto toDto(Playlist playlist) {
        List<SongResponseDto> songs = playlist.getSongs().stream()
                .map(song -> new SongResponseDto(
                        song.getId(),
                        song.getTitle(),
                        song.getArtist().getName(),
                        song.getAlbum() != null ? song.getAlbum().getTitle() : null,
                        song.getGenre() != null ? song.getGenre().getName() : null,
                        song.getDurationSeconds(),
                        song.getUrl(),
                        song.getPlayCount()
                ))
                .collect(Collectors.toList());

        return new PlaylistResponseDto(
                playlist.getId(),
                playlist.getName(),
                playlist.getUser().getUsername(),
                songs
        );
    }

    private List<Song> resolveSongs(List<Long> songIds) {
        return songIds.stream()
                .map(id -> songRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Şarkı bulunamadı. ID: " + id)))
                .collect(Collectors.toList());
    }
}
