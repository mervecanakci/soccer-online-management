package com.turkcell.socceronlinemanagement.repository;

import com.turkcell.socceronlinemanagement.model.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Integer> {

//    boolean existsByNameIgnoreCase(String leagueName);

}
