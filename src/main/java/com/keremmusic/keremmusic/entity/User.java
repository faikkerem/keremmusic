package com.keremmusic.keremmusic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Kullanıcı adı boş olamaz")
    @Column(unique = true, nullable = false)
    private String username;

    // E-posta alanını buraya ekliyoruz
    @NotBlank(message = "E-posta boş olamaz")
    @Email(message = "Geçerli bir e-posta adresi giriniz")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(min = 4, message = "Şifre en az 4 karakter olmalıdır")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Playlist> playlists = new ArrayList<>();

    public enum Role {
        USER,
        ADMIN
    }
}