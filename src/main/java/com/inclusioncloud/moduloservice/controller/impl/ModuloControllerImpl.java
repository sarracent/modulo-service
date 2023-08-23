package com.inclusioncloud.moduloservice.controller.impl;

import com.inclusioncloud.moduloservice.controller.ModuloController;
import com.inclusioncloud.moduloservice.model.request.ModuloRequest;
import com.inclusioncloud.moduloservice.model.response.ModuloResponse;
import com.inclusioncloud.moduloservice.service.ModuloService;
import com.inclusioncloud.moduloservice.service.Resilience4jService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.inclusioncloud.moduloservice.constants.Constants.OK_MSG;
import static com.inclusioncloud.moduloservice.constants.Constants.ZERO_MSG;

@RestController
@AllArgsConstructor
public class ModuloControllerImpl implements ModuloController {
    private final ModuloService moduloService;
    private final Resilience4jService resilience4JService;

    @Override
    public ResponseEntity<ModuloResponse> calculateModulo(final List<Integer> integers) {

        var request = ModuloRequest.builder()
                .x(integers.get(0))
                .y(integers.get(1))
                .n(integers.get(2))
                .build();

        var response = ModuloResponse.builder()
                .maximumInteger(resilience4JService.executeCalculateModulo(() -> moduloService
                        .calculateMaximumK(request.getX(), request.getY(), request.getN())))
                .resultCode(ZERO_MSG)
                .resultMessage(OK_MSG)
                .build();

        return ResponseEntity.ok()
                .body(response);
    }

    @Override
    public ResponseEntity<ModuloResponse> calculateModulo(final ModuloRequest moduloRequest) {

        var response = ModuloResponse.builder()
                .maximumInteger(resilience4JService.executeCalculateModulo(() -> moduloService
                        .calculateMaximumK(moduloRequest.getX(), moduloRequest.getY(), moduloRequest.getN())))
                .resultCode(ZERO_MSG)
                .resultMessage(OK_MSG)
                .build();

        return ResponseEntity.ok()
                .body(response);
    }
}
