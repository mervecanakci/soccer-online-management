package com.turkcell.socceronlinemanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    //  private String country;
    private String password;
//    private String lastName;
//    private String firstName;

//    @ManyToOne
//    @JoinColumn(name = "team_id")
//    private Team team;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
