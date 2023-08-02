package com.turkcell.socceronlinemanagement.service.user;


import com.turkcell.socceronlinemanagement.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
   // private String name;
    private String email;
    private  String password;
   // private String username;
    private Role role;
  //  private String email;
}