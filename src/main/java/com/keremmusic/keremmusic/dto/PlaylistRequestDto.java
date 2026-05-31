package com.keremmusic.keremmusic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistRequestDto {

    @NotBlank(message = "Playlist adı boş olamaz")
    private String name;

    @NotNull(message = "Şarkı listesi boş olamaz")
    private List<Long> songIds;
}
