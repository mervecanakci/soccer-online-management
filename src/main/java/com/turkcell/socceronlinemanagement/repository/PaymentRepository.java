package com.turkcell.socceronlinemanagement.repository;


import com.turkcell.socceronlinemanagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // uniq olsun istediğimiz için:
    //bu sorguyu transfer de kullanıcaz caRDnUMBER UNİQ DİYE ONLA YAPTIK
    Payment findByTeamValue(double teamValue);

    boolean existsByUserIdAndTeamIdAndPlayerId(
            Integer userId, Integer teamId, Integer playerId);

}
