package com.keremmusic.keremmusic.repository;

import com.keremmusic.keremmusic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email); // İşte eksik olan buydu!
}