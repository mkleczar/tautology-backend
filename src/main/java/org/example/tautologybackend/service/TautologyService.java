package org.example.tautologybackend.service;

import org.example.tautology.Expression;
import org.example.tautology.ExpressionHelper;
import org.example.tautology.context.Context;
import org.example.tautology.openapi.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

import static org.example.tautology.Scanner.scan;

@Service
public class TautologyService {

    public ParametersResponse parameters(ParametersRequest request) {
        Expression expression = scan(request.getExpression());
        Set<String> parameters = ExpressionHelper.collectVariables(expression);
        return new ParametersResponse()
                        .parameters(new ArrayList<>(parameters))
                        .expression(expression.asText());
    }

    public ValidationResponse validate(ValidationRequest request) {
        Context context = request.getParameters().stream()
                .collect(
                        Context::builder,
                        (b, p) -> b.param(p.getName(), p.getValue()),
                        Context.Builder::merge)
                .build();
        Expression expression = scan(request.getExpression());
        Boolean value = expression.validate(context);
        return new ValidationResponse()
                        .expression(expression.asText())
                        .parameters(request.getParameters())
                        .value(value);
    }

    public TautologyResponse tautology(TautologyRequest request) {
        Expression expression = scan(request.getExpression());
        boolean isTautology = ExpressionHelper.isTautology(expression);
        return new TautologyResponse()
                .expression(expression.asText())
                .value(isTautology);
    }
}
