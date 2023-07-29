package com.turkcell.socceronlinemanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String teamName;
    private BigDecimal teamValue = BigDecimal.valueOf(5000000);
    private String teamCountry;
  //  private BigDecimal totalBalance; //todo: default takım değeri + oyuncuların marketValue toplamı


    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL)
    private User user;
}
