package com.turkcell.socceronlinemanagement.api;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.socceronlinemanagement.service.team.TeamRequest;
import com.turkcell.socceronlinemanagement.service.team.TeamResponse;
import com.turkcell.socceronlinemanagement.service.team.TeamService;
import com.turkcell.socceronlinemanagement.service.transfer.TransferPlayerRequest;

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

@ContextConfiguration(classes = {TeamController.class})
@ExtendWith(SpringExtension.class)
class TeamControllerTest {
    @Autowired
    private TeamController teamController;

    @MockBean
    private TeamService teamService;

    @Test
    void testGetAll() throws Exception {
        when(teamService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/teams");
        MockMvcBuilders.standaloneSetup(teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetById() throws Exception {
        when(teamService.getById(anyInt())).thenReturn(new TeamResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/teams/{id}", 1);
        MockMvcBuilders.standaloneSetup(teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":0,\"teamName\":null,\"teamValue\":0.0,\"teamCountry\":null,\"players\":null}"));
    }

    @Test
    void testGetById2() throws Exception {
        when(teamService.getAll()).thenReturn(new ArrayList<>());
        when(teamService.getById(anyInt())).thenReturn(new TeamResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/teams/{id}", "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdate() throws Exception {
        when(teamService.update(anyInt(), Mockito.<TeamRequest>any())).thenReturn(new TeamResponse());

        TeamRequest teamRequest = new TeamRequest();
        teamRequest.setTeamValue(10.0d);
        teamRequest.setUserId(1);
        String content = (new ObjectMapper()).writeValueAsString(teamRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/teams/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":0,\"teamName\":null,\"teamValue\":0.0,\"teamCountry\":null,\"players\":null}"));
    }

    @Test
    void testAdd() throws Exception {
        when(teamService.getAll()).thenReturn(new ArrayList<>());

        TeamRequest teamRequest = new TeamRequest();
        teamRequest.setTeamValue(10.0d);
        teamRequest.setUserId(1);
        String content = (new ObjectMapper()).writeValueAsString(teamRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(teamService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/teams/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(teamController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testDelete2() throws Exception {
        doNothing().when(teamService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/teams/{id}", 1);
        requestBuilder.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(teamController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testTransferPlayer() throws Exception {
        when(teamService.addTransferPlayer(Mockito.<TransferPlayerRequest>any())).thenReturn(new TeamResponse());

        TransferPlayerRequest transferPlayerRequest = new TransferPlayerRequest();
        transferPlayerRequest.setPlayerId(1);
        transferPlayerRequest.setPlayerMarketValue(10.0d);
        transferPlayerRequest.setPrice(10.0d);
        transferPlayerRequest.setTeamId(1);
        String content = (new ObjectMapper()).writeValueAsString(transferPlayerRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/teams/transfer/player")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":0,\"teamName\":null,\"teamValue\":0.0,\"teamCountry\":null,\"players\":null}"));
    }
}

