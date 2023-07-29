package com.turkcell.socceronlinemanagement.service.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer id;
    private String email;
    //  private String country;
    private String password;
    //    private String lastName;
//    private String firstName;
//    private int teamId;
//    private String teamName;
}
