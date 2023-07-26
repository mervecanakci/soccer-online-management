package com.turkcell.socceronlinemanagement.repository;

import com.turkcell.socceronlinemanagement.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    Transfer findByPlayerIdAndIsCompletedIsFalse(int playerId);

    boolean existsByPlayerIdAndIsCompletedIsFalse(int playerId);
    // false olarak aldık çünkü add kısmında oyuncu transfer listesinde mi
    // buna bakması lazım öncelikle

}
