package com.turkcell.socceronlinemanagement.api;

import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.socceronlinemanagement.model.enums.Position;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import com.turkcell.socceronlinemanagement.service.player.PlayerRequest;
import com.turkcell.socceronlinemanagement.service.player.PlayerResponse;
import com.turkcell.socceronlinemanagement.service.player.PlayerService;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {PlayerController.class})
@ExtendWith(SpringExtension.class)
class PlayerControllerTest {
    @Autowired
    private PlayerController playerController;

    @MockBean
    private PlayerService playerService;

    @Test
    void testGetAll() throws Exception {
        when(playerService.getAll(anyBoolean())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/players");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("includeTransfer", String.valueOf(true));
        MockMvcBuilders.standaloneSetup(playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetById() throws Exception {
        when(playerService.getById(anyInt())).thenReturn(
                new PlayerResponse(1, 1, 1, "GB", "Doe", "Jane", 10.0d, Position.GOALKEEPER, TransferState.TRANSFERRED));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/players/{id}", 1);
        MockMvcBuilders.standaloneSetup(playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"teamId\":1,\"age\":1,\"country\":\"GB\",\"lastName\":\"Doe\",\"firstName\":\"Jane\",\"marketValue\":10.0,"
                                        + "\"position\":\"GOALKEEPER\",\"transferState\":\"TRANSFERRED\"}"));
    }

    @Test
    void testUpdate() throws Exception {
        when(playerService.update(anyInt(), Mockito.<PlayerRequest>any())).thenReturn(
                new PlayerResponse(1, 1, 1, "GB", "Doe", "Jane", 10.0d, Position.GOALKEEPER, TransferState.TRANSFERRED));

        PlayerRequest playerRequest = new PlayerRequest();
        playerRequest.setMarketValue(10.0d);
        playerRequest.setTeamId(1);
        playerRequest.setTransferState(TransferState.TRANSFERRED);
        String content = (new ObjectMapper()).writeValueAsString(playerRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/players/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"teamId\":1,\"age\":1,\"country\":\"GB\",\"lastName\":\"Doe\",\"firstName\":\"Jane\",\"marketValue\":10.0,"
                                        + "\"position\":\"GOALKEEPER\",\"transferState\":\"TRANSFERRED\"}"));
    }

    @Test
    void testAdd() throws Exception {
        when(playerService.getAll(anyBoolean())).thenReturn(new ArrayList<>());

        PlayerRequest playerRequest = new PlayerRequest();
        playerRequest.setMarketValue(10.0d);
        playerRequest.setTeamId(1);
        playerRequest.setTransferState(TransferState.TRANSFERRED);
        String content = (new ObjectMapper()).writeValueAsString(playerRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(playerService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/players/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(playerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testDelete2() throws Exception {
        doNothing().when(playerService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/players/{id}", 1);
        requestBuilder.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(playerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

