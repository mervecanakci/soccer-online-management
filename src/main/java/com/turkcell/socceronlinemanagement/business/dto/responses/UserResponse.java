package com.turkcell.socceronlinemanagement.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int id;
    private String email;
    //  private String country;
    private String password;
    //    private String lastName;
//    private String firstName;
//    private int teamId;
//    private String teamName;
}
