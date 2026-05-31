package com.keremmusic.keremmusic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistResponseDto {
    private Long id;
    private String name;
    private String username;
    private List<SongResponseDto> songs;
}
