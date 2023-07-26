package com.turkcell.socceronlinemanagement.repository;

import com.turkcell.socceronlinemanagement.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    //  boolean existsByNameIgnoreCase(String name);
    //todo: bu mesela aynı şekilde iki isim yazılamaz sen bunu emaile yap user business

}
