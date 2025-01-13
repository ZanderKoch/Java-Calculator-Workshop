package zander.lexicon.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code Tokenizer} class processes a string provided upon instantiation into a list of {@code Token} objects.
 */
public class Tokenizer {
    private String input;
    private int position;

    public Tokenizer(String input) {
        this.input = input;
        this.position = 0;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile(
                "\\d+(\\.\\d+)?|[+\\-*/()]"
        );
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String match = matcher.group();
            if (match.matches("\\d+(\\.\\d+)?")) {
                tokens.add(Token.createToken(Token.TokenType.NUMBER, match));
            } else if (match.equals("+")) {
                tokens.add(Token.createToken(Token.TokenType.PLUS));
            } else if (match.equals("-")) {
                tokens.add(Token.createToken(Token.TokenType.MINUS));
            } else if (match.equals("*")) {
                tokens.add(Token.createToken(Token.TokenType.MULTIPLY));
            } else if (match.equals("/")) {
                tokens.add(Token.createToken(Token.TokenType.DIVIDE));
            } else if (match.equals("(")) {
                tokens.add(Token.createToken(Token.TokenType.LEFT_PAREN));
            } else if (match.equals(")")) {
                tokens.add(Token.createToken(Token.TokenType.RIGHT_PAREN));
            } else {
                throw new IllegalArgumentException("Unexpected token: " + match);
            }
        }

        return tokens;
    }
}
