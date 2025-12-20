package laba3.hmsite.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
import laba3.hmsite.service.SatelliteService;

@WebMvcTest(SatelliteController.class)
@Import(SecurityConfig.class)
@MockBean(CustomAccessDeniedHandler.class)
class SatelliteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SatelliteService satelliteService;

    @MockBean
    private PlanetService planetService;

    @Test
    @WithMockUser(roles = "USER")
    void listSatellitesWithUserReturnsOk() throws Exception {
        when(satelliteService.getAllSatellites()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/satellites"))
                .andExpect(status().isOk())
                .andExpect(view().name("satellites"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void addSatelliteFormAccessibleForUserInTestSlice() throws Exception {
        mockMvc.perform(get("/satellites/add"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void addSatelliteFormAllowedForAdminAndProvidesPlanets() throws Exception {
        when(planetService.getAllPlanets()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/satellites/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-satellite"))
                .andExpect(model().attributeExists("satellite"))
                .andExpect(model().attributeExists("planets"));
    }
}
