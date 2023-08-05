package com.turkcell.socceronlinemanagement.repository;

import com.turkcell.socceronlinemanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email); //findByEmail metodu ile email adresine göre kullanıcı arayabiliriz
    boolean existsByEmailIgnoreCase(String email); // emaili kontrol ediyor çünkü email unique olmalı

}
