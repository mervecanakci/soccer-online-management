package com.turkcell.socceronlinemanagement.service.team;

import com.github.javafaker.Address;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequest {


//
//    @Min(0)
//    private Integer userId;

//    @Min(0)
//    private Integer leagueId;

    @NotNull
    @Length(min = 2, message = "Length must be greater than 2!")
    private String teamName;

    @NotNull
    private String teamCountry;

    //@Value("${team.value:5000000}")
    private BigDecimal teamValue; //todo: Takım değeri (oyuncu değerlerinin toplamı)


    //private double totalBalance; // default takım değeri + oyuncuların toplamı

}
//TODO: BURADA; HESAPLANIP RESPONSE DA OKUNACAK DEĞERLER VARRR totalBalance