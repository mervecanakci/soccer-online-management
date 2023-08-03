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
    private double teamValue;
    private String playerName;
    private String playerCountry;
    private double playerMarketValue = 1000000;
    private double  price;
    private LocalDateTime dateOfTransfer;
    private boolean isCompleted;
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

}

