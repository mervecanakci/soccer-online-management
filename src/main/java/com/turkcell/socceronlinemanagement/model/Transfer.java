package com.turkcell.socceronlinemanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int teamId;
    private int oldTeamId;
    private int newTeamId;
    private String teamName;
    private double teamValue;
    // private String playerId;
    private String playerName;
    private String playerCountry;
    private double playerMarketValue;
    private LocalDateTime dateOfTransfer;
    private boolean isCompleted;

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