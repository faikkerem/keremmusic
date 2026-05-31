package com.keremmusic.keremmusic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumRequestDto {
    @NotBlank(message = "Albüm adı boş olamaz")
    private String title;

    @NotNull(message = "Sanatçı ID'si gerekli")
    private Long artistId;

    private LocalDate releaseDate;
    private String coverUrl;
}
