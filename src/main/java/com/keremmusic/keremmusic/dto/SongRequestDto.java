package com.keremmusic.keremmusic.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongRequestDto {

    @NotBlank(message = "Şarkı adı boş bırakılamaz")
    private String title;

    @NotNull(message = "Sanatçı ID'si gerekli")
    private Long artistId;

    private Long albumId;

    private Long genreId;

    @Min(value = 1, message = "Süre en az 1 saniye olmalıdır")
    private Integer durationSeconds;

    @NotBlank(message = "URL boş bırakılamaz")
    private String url;
}
