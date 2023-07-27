package com.turkcell.socceronlinemanagement.model;

import com.turkcell.socceronlinemanagement.model.enums.Position;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private int age; // todo: 18 48 arası random method ile oluştur
    //    private double height;
    //    private double weight;
    //   private int teamId;
    private String country;
    private String firstName;
    private String lastName;
    private double marketValue = 1000000;
    @Enumerated(EnumType.STRING)
    private Position position;
    @Enumerated(EnumType.STRING)
    private TransferState transferState; //?


    //    @ManyToMany(mappedBy = "players")
//    private List<Transfer> transfers = new ArrayList<>();
    @OneToMany(mappedBy = "player")
    private List<Transfer> transfers;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    //TODO:RANDOM NAME GENERATOR KULLAn 1000-10000000 arası random sayı üret ve o sayı kadar oyuncu oluştur ve bu oyuncuları bir listeye ekle
    //TODO: oyunculara 18-48 arasıında rastgele yaş ata, rastgele ülke, isim ve soy isim de oluştur
    //TODO: son olarak da varsayılan 1000000 marketValue ata her bir oyuncu için bu sonradan değiştirilebilir olsun
    //TODO: bu listeyi de bir takıma ekle ve takımı da bir lig ekle ve ligi de bir listeye ekle ve bu listeyle birlikte bir lig oluştur
}
