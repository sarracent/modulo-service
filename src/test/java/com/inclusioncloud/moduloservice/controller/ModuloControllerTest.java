package com.inclusioncloud.moduloservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inclusioncloud.moduloservice.controller.impl.ModuloControllerImpl;
import com.inclusioncloud.moduloservice.model.request.ModuloRequest;
import com.inclusioncloud.moduloservice.service.ModuloService;
import com.inclusioncloud.moduloservice.service.Resilience4jService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ModuloControllerImpl.class)
class ModuloControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private ModuloService moduloService;
    @Mock
    private Resilience4jService resilience4JService;
    private ModuloController moduloController;

    @BeforeEach
    void setUp() {
        moduloController = new ModuloControllerImpl(moduloService, resilience4JService);
    }

    @Test
    public void testCalculateModuloGet() throws Exception {
        when(resilience4JService.executeCalculateModulo(any())).thenReturn(12339);

        mockMvc.perform(get("/modulo/calculate")
                        .param("integers", "7,5,12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maximumInteger").value(12339))
                .andExpect(jsonPath("$.resultCode").value("0"))
                .andExpect(jsonPath("$.resultMessage").value("OK"));

        var respose = moduloController.calculateModulo(List.of(5,7,12345));

        assertEquals(12339, respose.getBody().getMaximumInteger());
        assertEquals("0", respose.getBody().getResultCode());
        assertEquals("OK", respose.getBody().getResultMessage());
    }

    @Test
    public void testCalculateModuloPost() throws Exception {

        var request = ModuloRequest.builder().x(2).y(0).n(999999999).build();

        mockMvc.perform(post("/modulo/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maximumInteger").value(999999998))
                .andExpect(jsonPath("$.resultCode").value("0"))
                .andExpect(jsonPath("$.resultMessage").value("OK"));

        var respose = moduloController.calculateModulo(request);

        assertEquals("0", respose.getBody().getResultCode());
        assertEquals("OK", respose.getBody().getResultMessage());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}