package com.turkcell.socceronlinemanagement.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.socceronlinemanagement.model.enums.Role;
import com.turkcell.socceronlinemanagement.service.user.UserAuthRequest;
import com.turkcell.socceronlinemanagement.service.user.UserRegisterRequest;
import com.turkcell.socceronlinemanagement.service.user.UserResponse;
import com.turkcell.socceronlinemanagement.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void testRegister() throws Exception {
        when(userService.register(Mockito.<UserRegisterRequest>any())).thenReturn(new UserResponse());

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("merve@gmail.com");
        userRegisterRequest.setPassword("12345678");
        userRegisterRequest.setRole(Role.USER);
        String content = (new ObjectMapper()).writeValueAsString(userRegisterRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":0,\"token\":null,\"user\":null,\"email\":null,\"password\":null,\"teams\":null}"));
    }

    @Test
    void testAdd() throws Exception {
        when(userService.getAll()).thenReturn(new ArrayList<>());

        UserAuthRequest userAuthRequest = new UserAuthRequest();
        userAuthRequest.setEmail("merve@gmail.com");
        userAuthRequest.setPassword("12345678");
        String content = (new ObjectMapper()).writeValueAsString(userAuthRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testAuthenticate() throws Exception {
        when(userService.authenticate(Mockito.<UserAuthRequest>any())).thenReturn(new UserResponse());

        UserAuthRequest userAuthRequest = new UserAuthRequest();
        userAuthRequest.setEmail("merve@gmail.com");
        userAuthRequest.setPassword("12345678");
        String content = (new ObjectMapper()).writeValueAsString(userAuthRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":0,\"token\":null,\"user\":null,\"email\":null,\"password\":null,\"teams\":null}"));
    }

    @Test
    void testGetAll() throws Exception {
        when(userService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetById() throws Exception {
        when(userService.getById(anyInt())).thenReturn(new UserResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/{id}", 1);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":0,\"token\":null,\"user\":null,\"email\":null,\"password\":null,\"teams\":null}"));
    }

    @Test
    void testGetById2() throws Exception {
        when(userService.getAll()).thenReturn(new ArrayList<>());
        when(userService.getById(anyInt())).thenReturn(new UserResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/{id}", "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdate() throws Exception {
        when(userService.update(anyInt(), Mockito.<UserAuthRequest>any())).thenReturn(new UserResponse());

        UserAuthRequest userAuthRequest = new UserAuthRequest();
        userAuthRequest.setEmail("merve@gmail.com");
        userAuthRequest.setPassword("12345678");
        String content = (new ObjectMapper()).writeValueAsString(userAuthRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":0,\"token\":null,\"user\":null,\"email\":null,\"password\":null,\"teams\":null}"));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(userService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/users/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testDelete2() throws Exception {
        doNothing().when(userService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/users/{id}", 1);
        requestBuilder.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

