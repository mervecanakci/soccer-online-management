package com.turkcell.socceronlinemanagement.service.user;

import com.turkcell.socceronlinemanagement.model.User;
import com.turkcell.socceronlinemanagement.service.team.TeamResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int id;

    //kayıt olduğunda token dönecek
    private String token;
    private User user;

    private String email;
    private String password;
    private List<TeamResponse> teams;
}