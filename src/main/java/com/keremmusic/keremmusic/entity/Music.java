package com.keremmusic.keremmusic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "musics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Şarkı adı boş bırakılamaz")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Sanatçı adı boş bırakılamaz")
    @Column(nullable = false)
    private String artist;

    private String album;

    private String duration; // Şarkı süresi (Örn: "3:45")
}