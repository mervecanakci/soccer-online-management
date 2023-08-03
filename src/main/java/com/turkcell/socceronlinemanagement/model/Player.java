package com.turkcell.socceronlinemanagement.model;

import com.turkcell.socceronlinemanagement.model.enums.Position;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
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
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    private String country;
    private String firstName;
    private String lastName;
    private double marketValue = 1000000.0;
    @Enumerated(EnumType.STRING)
    private Position position;
    @Enumerated(EnumType.STRING)
    private TransferState transferState;


    //    @ManyToMany(mappedBy = "players")
//    private List<Transfer> transfers = new ArrayList<>();
    @OneToMany(mappedBy = "player")
    private List<Transfer> transfers;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;



}
