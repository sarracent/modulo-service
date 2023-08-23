package com.inclusioncloud.moduloservice.service.impl;

import com.inclusioncloud.moduloservice.service.ModuloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.relational.core.sql.In;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class ModuloServiceImplTest {
    private ModuloService moduloService;

    @BeforeEach
    void setUp() {
        moduloService = new ModuloServiceImpl();
    }

    @Test
    void calculateMaximumK() {
        final Integer k1 = moduloService.calculateMaximumK(7,5,12345);
        final Integer k2 = moduloService.calculateMaximumK(5,0,4);
        final Integer k3 = moduloService.calculateMaximumK(10,5,15);
        final Integer k4 = moduloService.calculateMaximumK(17,8,54321);
        final Integer k5 = moduloService.calculateMaximumK(499999993 ,9,1000000000);
        final Integer k6 = moduloService.calculateMaximumK(10,5,187);
        final Integer k7 = moduloService.calculateMaximumK(2 ,0,999999999);

        assertNotNull(k1);
        assertEquals(12339, k1);

        assertNotNull(k2);
        assertEquals(0, k2);

        assertNotNull(k3);
        assertEquals(15, k3);

        assertNotNull(k4);
        assertEquals(54306, k4);

        assertNotNull(k5);
        assertEquals(999999995, k5);

        assertNotNull(k6);
        assertEquals(185, k6);

        assertNotNull(k7);
        assertEquals(999999998, k7);
    }
}