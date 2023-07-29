package com.turkcell.socceronlinemanagement.service.administrator;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorRequest {

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;


}
