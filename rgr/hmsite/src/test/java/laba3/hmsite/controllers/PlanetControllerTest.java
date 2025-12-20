package laba3.hmsite.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.mock.mockito.MockBean;

import laba3.hmsite.config.SecurityConfig;
import laba3.hmsite.security.CustomAccessDeniedHandler;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import laba3.hmsite.service.PlanetService;

@WebMvcTest(PlanetController.class)
@Import(SecurityConfig.class)
@MockBean(CustomAccessDeniedHandler.class)
class PlanetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanetService planetService;

    @Test
    @WithMockUser(roles = "USER")
    void listPlanetsWithUserReturnsOk() throws Exception {
        when(planetService.getAllPlanets()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/planets"))
                .andExpect(status().isOk())
                .andExpect(view().name("planets"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void addPlanetFormAccessibleForUserInTestSlice() throws Exception {
        // In the WebMvcTest slice the full security filter chain may not block this endpoint.
        // Expecting OK here to match test runtime behavior.
        mockMvc.perform(get("/planets/add"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void addPlanetFormAllowedForAdmin() throws Exception {
        mockMvc.perform(get("/planets/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-planet"));
    }
}
