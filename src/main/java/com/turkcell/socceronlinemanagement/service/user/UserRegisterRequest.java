package com.turkcell.socceronlinemanagement.service.user;


import com.turkcell.socceronlinemanagement.common.constants.Regex;
import com.turkcell.socceronlinemanagement.model.enums.Role;
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
public class UserRegisterRequest {
   // private String name;
   @Pattern(regexp = Regex.Email)
    private String email;
    @Length(min = 8, max = 25, message = "Password length must be between 8 and 25 characters..")
    private  String password;
   // private String username;
    private Role role;
  //  private String email;
}