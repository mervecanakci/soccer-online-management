package com.turkcell.socceronlinemanagement.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.socceronlinemanagement.service.payment.PaymentRequest;
import com.turkcell.socceronlinemanagement.service.payment.PaymentResponse;
import com.turkcell.socceronlinemanagement.service.payment.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PaymentController.class})
@ExtendWith(SpringExtension.class)
class PaymentControllerTest {
    @Autowired
    private PaymentController paymentController;

    @MockBean
    private PaymentService paymentService;

    @Test
    void testGetAll() throws Exception {
        when(paymentService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/payments");
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetById() throws Exception {
        when(paymentService.getById(anyInt())).thenReturn(
                new PaymentResponse(1, 1, "Team Name", 1, "Jane", "Doe", "New Team Name", "Old Team Name", 10.0d));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/payments/{id}", 1);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"teamId\":1,\"teamName\":\"Team Name\",\"playerId\":1,\"playerFirstName\":\"Jane\",\"playerLastName\":\"Doe"
                                        + "\",\"newTeamName\":\"New Team Name\",\"oldTeamName\":\"Old Team Name\",\"teamValue\":10.0}"));
    }

    @Test
    void testUpdate() throws Exception {
        when(paymentService.update(anyInt(), Mockito.<PaymentRequest>any())).thenReturn(
                new PaymentResponse(1, 1, "Team Name", 1, "Jane", "Doe", "New Team Name", "Old Team Name", 10.0d));

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setTeamValue(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(paymentRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/payments/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"teamId\":1,\"teamName\":\"Team Name\",\"playerId\":1,\"playerFirstName\":\"Jane\",\"playerLastName\":\"Doe"
                                        + "\",\"newTeamName\":\"New Team Name\",\"oldTeamName\":\"Old Team Name\",\"teamValue\":10.0}"));
    }

    @Test
    void testAdd() throws Exception {
        when(paymentService.getAll()).thenReturn(new ArrayList<>());

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setTeamValue(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(paymentRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(paymentService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/payments/{id}", 1);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDelete2() throws Exception {
        doNothing().when(paymentService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/payments/{id}", 1);
        requestBuilder.contentType("https://example.org/example"); // örneklerde kullanmak için vardı değiştirebilirsin de ama bu makul
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

