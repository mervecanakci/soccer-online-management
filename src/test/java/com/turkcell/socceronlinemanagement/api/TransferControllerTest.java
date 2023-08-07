package com.turkcell.socceronlinemanagement.api;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.socceronlinemanagement.service.transfer.TransferRequest;
import com.turkcell.socceronlinemanagement.service.transfer.TransferResponse;
import com.turkcell.socceronlinemanagement.service.transfer.TransferService;

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

@ContextConfiguration(classes = {TransferController.class})
@ExtendWith(SpringExtension.class)
class TransferControllerTest {
    @Autowired
    private TransferController transferController;

    @MockBean
    private TransferService transferService;

    @Test
    void testGetAll() throws Exception {
        when(transferService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/transfers");
        MockMvcBuilders.standaloneSetup(transferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetById() throws Exception {
        when(transferService.getById(anyInt())).thenReturn(new TransferResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/transfers/{id}", 1);
        MockMvcBuilders.standaloneSetup(transferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"teamName\":null,\"teamValue\":0.0,\"playerId\":0,\"playerName\":null,\"price\":0.0,\"dateOfTransfer"
                                        + "\":null}"));
    }

    @Test
    void testGetById2() throws Exception {
        when(transferService.getAll()).thenReturn(new ArrayList<>());
        when(transferService.getById(anyInt())).thenReturn(new TransferResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/transfers/{id}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(transferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdate() throws Exception {
        when(transferService.update(anyInt(), Mockito.<TransferRequest>any())).thenReturn(new TransferResponse());

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setPlayerId(1);
        transferRequest.setPlayerMarketValue(10.0d);
        transferRequest.setPlayerName("Player Name");
        transferRequest.setPrice(10.0d);
        transferRequest.setTeamName("Team Name");
        transferRequest.setTeamValue(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(transferRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/transfers/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"teamName\":null,\"teamValue\":0.0,\"playerId\":0,\"playerName\":null,\"price\":0.0,\"dateOfTransfer"
                                        + "\":null}"));
    }

    @Test
    void testAdd() throws Exception {
        when(transferService.getAll()).thenReturn(new ArrayList<>());

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setPlayerId(1);
        transferRequest.setPlayerMarketValue(10.0d);
        transferRequest.setPlayerName("Player Name");
        transferRequest.setPrice(10.0d);
        transferRequest.setTeamName("Team Name");
        transferRequest.setTeamValue(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(transferRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transferController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(transferService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/transfers/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(transferController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testDelete2() throws Exception {
        doNothing().when(transferService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/transfers/{id}", 1);
        requestBuilder.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(transferController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

