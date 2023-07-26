package com.turkcell.socceronlinemanagement.business.abstracts;

import com.turkcell.socceronlinemanagement.business.dto.requests.UserRequest;
import com.turkcell.socceronlinemanagement.business.dto.responses.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();

    UserResponse getById(int id);

    UserResponse add(UserRequest request);

    UserResponse update(int id, UserRequest request);

    void delete(int id);
    
}
