package com.keremmusic.keremmusic.controller;

import com.keremmusic.keremmusic.dto.PlaylistRequestDto;
import com.keremmusic.keremmusic.dto.PlaylistResponseDto;
import com.keremmusic.keremmusic.service.PlaylistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PlaylistResponseDto>> getMyPlaylists(Principal principal) {
        return ResponseEntity.ok(playlistService.getPlaylistsForUser(principal.getName()));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PlaylistResponseDto> createPlaylist(Principal principal,
                                                              @Valid @RequestBody PlaylistRequestDto request) {
        return ResponseEntity.ok(playlistService.createPlaylist(principal.getName(), request));
    }

    @PostMapping("/{playlistId}/songs/{songId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PlaylistResponseDto> addSongToPlaylist(Principal principal,
                                                                 @PathVariable Long playlistId,
                                                                 @PathVariable Long songId) {
        return ResponseEntity.ok(playlistService.addSongToPlaylist(playlistId, songId, principal.getName()));
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PlaylistResponseDto> removeSongFromPlaylist(Principal principal,
                                                                      @PathVariable Long playlistId,
                                                                      @PathVariable Long songId) {
        return ResponseEntity.ok(playlistService.removeSongFromPlaylist(playlistId, songId, principal.getName()));
    }
}
