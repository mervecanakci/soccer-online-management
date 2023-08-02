package com.turkcell.socceronlinemanagement.repository;

import com.turkcell.socceronlinemanagement.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    Transfer findByPlayerIdAndIsCompletedIsFalse(Integer playerId);
    // tablolara bakacak player id ve isCompleted false ise onu getiricek değilse zaten sorun yok yani transfer olmuş demektir
    boolean existsByPlayerIdAndIsCompletedIsFalse(Integer playerId);
    // false olarak aldık çünkü add kısmında oyuncu transfer listesinde mi
    // buna bakması lazım öncelikle

}
