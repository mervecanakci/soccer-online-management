package com.turkcell.socceronlinemanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.turkcell.socceronlinemanagement.model.Team;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String teamName;
    private int teamValue;
    private double playerMarketValue = 100000.0;
    private double  price;
    private LocalDateTime dateOfTransfer;
    private boolean isCompleted;
    private LocalDateTime endDate;

    //    @ManyToMany
//    @JoinTable(
//            name = "player_transfer",
//            joinColumns = @JoinColumn(name = "transfer_id"),
//            inverseJoinColumns = @JoinColumn(name = "player_id"))
//    private List<Player> players = new ArrayList<>();
//
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;



}

// todo:bonservis bedeli yeni kulüp tarafından ödenir