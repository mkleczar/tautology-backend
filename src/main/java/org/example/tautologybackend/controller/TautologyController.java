package org.example.tautologybackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tautology.ExpressionHelper;
import org.example.tautology.openapi.api.TautologyApi;
import org.example.tautology.openapi.model.ParametersRequest;
import org.example.tautology.openapi.model.ParametersResponse;
import org.example.tautology.openapi.model.ValidationRequest;
import org.example.tautologybackend.service.TautologyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Set;

@Slf4j
@RestController
public class TautologyController implements TautologyApi {

    private TautologyService service;

    public TautologyController(TautologyService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ParametersResponse> parameters(@Valid ParametersRequest parametersRequest) {
        Set<String> parameters = service.parameters(parametersRequest.getExpression());
        return ResponseEntity.ok(
                new ParametersResponse()
                        .parameters(new ArrayList<>(parameters))
        );
    }

    @Override
    public ResponseEntity<Object> validate(@Valid ValidationRequest validationRequest) {
        return TautologyApi.super.validate(validationRequest);
    }
}
