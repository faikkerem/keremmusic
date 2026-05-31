package com.keremmusic.keremmusic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongResponseDto {
    private Long id;
    private String title;
    private String artistName;
    private String albumTitle;
    private String genreName;
    private Integer durationSeconds;
    private String url;
    private Long playCount;
}
