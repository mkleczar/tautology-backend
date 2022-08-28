package org.example.tautologybackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tautology.Expression;
import org.example.tautology.ExpressionHelper;
import org.example.tautology.context.Context;
import org.example.tautology.openapi.api.TautologyApi;
import org.example.tautology.openapi.model.*;
import org.example.tautologybackend.service.TautologyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class TautologyController implements TautologyApi {

    private TautologyService service;

    public TautologyController(TautologyService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ParametersResponse> parameters(@Valid ParametersRequest parametersRequest) {
        Expression expression = service.parse(parametersRequest.getExpression());
        Set<String> parameters = service.parameters(expression);
        return ResponseEntity.ok(
                new ParametersResponse()
                        .parameters(new ArrayList<>(parameters))
                        .expression(expression.asText())
        );
    }

    @Override
    public ResponseEntity<ValidationResponse> validate(@Valid ValidationRequest validationRequest) {
        // TODO: use mapstruct to map request to model objects?
        Context context = validationRequest.getParameters().stream()
                .collect(
                        Context::builder,
                        (b, p) -> b.param(p.getName(), p.getValue()),
                        Context.Builder::merge)
                .build();
        Expression expression = service.parse(validationRequest.getExpression());
        Boolean value = service.validate(expression, context);
        return ResponseEntity.ok(
                new ValidationResponse()
                        .expression(expression.asText())
                        .parameters(validationRequest.getParameters())
                        .value(value)
        );
    }
}
