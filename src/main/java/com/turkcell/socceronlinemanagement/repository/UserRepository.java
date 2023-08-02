package com.turkcell.socceronlinemanagement.repository;

import com.turkcell.socceronlinemanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   // Optional<User> findByUsername(String username);
  //  Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
  //  User findByEmail(String email);
   // boolean existsByName(String name);

    //todo: Her kullanıcının yalnızca bir takımı olabilir (kullanıcı bir e-posta ile tanımlanır)
   // boolean existsByEmailIgnoreCase(String email);

//    boolean existsByEmail(String email);
}
//todo bak hangisi