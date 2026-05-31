package com.keremmusic.keremmusic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistRequestDto {
    @NotBlank(message = "Sanatçı adı boş olamaz")
    private String name;

    private String country;
    private String biography;
}
