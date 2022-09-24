package org.example.tautologybackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tautology.Expression;
import org.example.tautology.ExpressionHelper;
import org.example.tautology.context.Context;
import org.example.tautology.openapi.api.TautologyApi;
import org.example.tautology.openapi.model.*;
import org.example.tautologybackend.service.TautologyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin
public class TautologyController implements TautologyApi {

    private final TautologyService service;

    public TautologyController(TautologyService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ParametersResponse> parameters(@Valid ParametersRequest parametersRequest) {
        log.info("Controller method: parameters called with request: {}", parametersRequest);
        ParametersResponse response = service.parameters(parametersRequest);
        log.info("Controller method: parameters response is: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ValidationResponse> validate(@Valid ValidationRequest validationRequest) {
        log.info("Controller method: validate called with request: {}", validationRequest);
        ValidationResponse response = service.validate(validationRequest);
        log.info("Controller method: validate response is: {}", response);
        return ResponseEntity.ok(response);
    }
}
