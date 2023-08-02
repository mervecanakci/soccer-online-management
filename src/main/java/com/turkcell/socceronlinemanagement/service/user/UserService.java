package com.turkcell.socceronlinemanagement.service.user;

import java.util.List;

public interface UserService {
    UserResponse register(UserRegisterRequest request);
    UserResponse authenticate(UserAuthRequest request);
    List<UserResponse> getAll();
    UserResponse getById(Integer id);
    UserResponse add(UserAuthRequest request);
    UserResponse update(Integer id, UserAuthRequest request);
    void delete(Integer id);
}
