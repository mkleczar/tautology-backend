package org.example.tautologybackend.service;

import org.example.tautology.Expression;
import org.example.tautology.ExpressionHelper;
import org.example.tautology.context.Context;
import org.springframework.stereotype.Service;
import java.util.Set;

import static org.example.tautology.Scanner.scan;

@Service
public class TautologyService {

    public Expression parse(String expressionStr) {
        return scan(expressionStr);
    }

    public Set<String> parameters(Expression expression) {
        return ExpressionHelper.collectVariables(expression);
    }

    public boolean validate(Expression expression, Context context) {
        return expression.validate(context);
    }
}
