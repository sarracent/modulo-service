package com.inclusioncloud.moduloservice.controller;

import com.inclusioncloud.moduloservice.model.request.ModuloRequest;
import com.inclusioncloud.moduloservice.model.response.ModuloResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.inclusioncloud.moduloservice.constants.Constants.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@Tag(name = "Modulo Controller", description = "The Modulo Api")
@Validated
@RequestMapping(path = "/modulo")
public interface ModuloController {

    @GetMapping(value = "/calculate", consumes = {APPLICATION_JSON_VALUE})
    @Operation(summary = "Calculate Modulo", description = "Calculate Maximum Integer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESS_CODE, description = SUCCESS_MSG, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ModuloResponse.class))),
            @ApiResponse(responseCode = BADREQUEST_CODE, description = BADREQUEST_MSG, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ModuloResponse.class))),
            @ApiResponse(responseCode = INTERNALSERVER_CODE, description = INTERNALSERVER_MSG, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ModuloResponse.class))),
    })
    ResponseEntity<ModuloResponse> calculateModulo(@NotEmpty(message = "The list of integers must not be empty")
                                                   @RequestParam List<Integer> integers);

    @PostMapping(value = "/calculate", consumes = {APPLICATION_JSON_VALUE})
    @Operation(summary = "Calculate Modulo", description = "Calculate Maximum Integer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESS_CODE, description = SUCCESS_MSG, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ModuloResponse.class))),
            @ApiResponse(responseCode = BADREQUEST_CODE, description = BADREQUEST_MSG, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ModuloResponse.class))),
            @ApiResponse(responseCode = INTERNALSERVER_CODE, description = INTERNALSERVER_MSG, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ModuloResponse.class))),
    })
    ResponseEntity<ModuloResponse> calculateModulo(@Valid @RequestBody ModuloRequest moduloRequest);
}
