package com.turkcell.socceronlinemanagement.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DashboardController.class})
@ExtendWith(SpringExtension.class)
class DashboardControllerTest {
    @Autowired
    private DashboardController dashboardController;

    @Test
    void testAdd() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/dashboard/42");
        MockMvcBuilders.standaloneSetup(dashboardController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAdd2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/dashboard/42", "Uri Variables");
        MockMvcBuilders.standaloneSetup(dashboardController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testHelloWorld() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dashboard");
        MockMvcBuilders.standaloneSetup(dashboardController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome Dashboard"));
    }

    @Test
    void testHelloWorld2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dashboard");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(dashboardController)// standaloneSetup() metodu, Spring MVC test altyapısını kullanarak bir Controller'ı test etmek için kullanılır.
                .build()
                .perform(requestBuilder)// perform() metodu, bir HTTP isteğini simüle eder.
                .andExpect(MockMvcResultMatchers.status().isOk())  // andExpect() metodu, bir HTTP yanıtını doğrular.
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))//"text/plain;charset=ISO-8859-1":
                .andExpect(MockMvcResultMatchers.content().string("Welcome Dashboard"));
    }
}

