package com.turkcell.socceronlinemanagement.service.user;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();

    UserResponse getById(Integer id);

    UserResponse add(UserRequest request);

    UserResponse update(Integer id, UserRequest request);

    void delete(Integer id);
    
}
