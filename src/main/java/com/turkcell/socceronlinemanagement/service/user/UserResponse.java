package com.turkcell.socceronlinemanagement.service.user;

import com.turkcell.socceronlinemanagement.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    //kayıt olduğunda token dönecek
    private String token;
    private User user;
}