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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String teamName;
    private double teamValue = 5000000.0;
    private String teamCountry;



    @ManyToOne
    @JoinColumn(name = "league_id")
    public League league;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @OneToOne
//    @OneToOne(mappedBy = "team")
    @JoinColumn(name = "user_id")
    private User user;
}
