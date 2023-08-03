package com.turkcell.socceronlinemanagement.service.user;

import com.turkcell.socceronlinemanagement.common.constants.Regex;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthRequest {
   // private String email;

    private String email;


    private String password;
//
//    private String firstName;
//
//    private String lastName;

    // private Team team;

}