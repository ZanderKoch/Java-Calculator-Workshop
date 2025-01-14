package zander.lexicon.calculator;

import java.util.function.Function;
import java.util.regex.Pattern;

public enum TokenType {
    NUMBER(Pattern.compile("\\d+(\\.\\d+)?"), (node) -> node.getToken().getValue()),
    PLUS(Pattern.compile("\\s*\\+\\s*"), (node) -> {
        checkChildrenAreDoubles(node, "+");
        return (Double) node.getLeft().evaluate() + (Double) node.getRight().evaluate();
    }),
    MINUS(Pattern.compile("\\s*\\-\\s*"), (node) -> {
        checkChildrenAreDoubles(node, "-");
        return (Double) node.getLeft().evaluate() - (Double) node.getRight().evaluate();
    }),
    MULTIPLY(Pattern.compile("\\s*\\*\\s*"), (node) -> {
        checkChildrenAreDoubles(node, "*");
        return (Double) node.getLeft().evaluate() * (Double) node.getRight().evaluate();
    }),
    DIVIDE(Pattern.compile("\\s*\\/\\s*"), (node) -> {
        checkChildrenAreDoubles(node, "/");
        return (Double) node.getLeft().evaluate() / (Double) node.getRight().evaluate();
    }),
    LEFT_PAREN(Pattern.compile("\\s*\\(\\s*"), (node) -> {
        throw new IllegalStateException("Token LEFT_PAREN ('(') is not meant to be evaluated");
    }),
    RIGHT_PAREN(Pattern.compile("\\s*\\)\\s*"), (node) -> {
        throw new IllegalStateException("Token RIGHT_PAREN (')') is not meant to be evaluated");
    })
    ;
    private final Pattern pattern;
    private final Function<Node, Object> evaluate;

    TokenType(Pattern pattern, Function<Node, Object> evaluate) {
        this.pattern = pattern;
        this.evaluate = evaluate;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Function<Node, Object> getEvaluate() {
        return evaluate;
    }

    private static void checkChildrenAreDoubles(Node node, String operator) {
        if (!((node.getLeft().evaluate() instanceof Double) && (node.getRight().evaluate() instanceof Double))){
            System.out.println((node.getLeft().evaluate()));
            System.out.println((node.getRight().evaluate()));
            throw new IllegalArgumentException("Invalid operands for operator '"
                    + operator + "'. both must be numbers");

        }
    }
}
