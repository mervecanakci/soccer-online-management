package com.turkcell.socceronlinemanagement.repository;

import com.turkcell.socceronlinemanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    //todo: Her kullanıcının yalnızca bir takımı olabilir (kullanıcı bir e-posta ile tanımlanır)
    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmail(String email);
}
//todo bak hangisi