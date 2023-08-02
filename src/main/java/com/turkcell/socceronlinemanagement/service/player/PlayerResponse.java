package com.turkcell.socceronlinemanagement.service.player;

import com.turkcell.socceronlinemanagement.model.enums.Position;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse {
    private Integer id;
    private Integer teamId;
    private String teamName;
    private Integer age;
    private String country;
    private String lastName;
    private String firstName;
    private double marketValue;
    private Position position;
    private TransferState transferState;
}
