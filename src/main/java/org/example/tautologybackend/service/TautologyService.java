package org.example.tautologybackend.service;

import org.example.tautology.Expression;
import org.example.tautology.ExpressionHelper;
import org.springframework.stereotype.Service;
import java.util.Set;

import static org.example.tautology.Scanner.scan;

@Service
public class TautologyService {

    public Set<String> parameters(String expressionStr) {
        Expression expression = scan(expressionStr);
        return ExpressionHelper.collectVariables(expression);
    }
}
