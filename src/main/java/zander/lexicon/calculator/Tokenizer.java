package zander.lexicon.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * The {@code Tokenizer} class processes a string provided upon instantiation into a list of {@code Token} objects.
 */
public class Tokenizer {
    private final String input;

    public Tokenizer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        String remainingInput = input;
        //iteratively check the input text against the pattern of each TokenType and add token of matched type to list
        while (!remainingInput.isEmpty()) {
            boolean matched = false;


            for (TokenType tokenType : TokenType.values()) {
                Matcher matcher = tokenType.getPattern().matcher(remainingInput);
                if (matcher.lookingAt()) {
                    String matchedText = matcher.group();
                    if (tokenType == TokenType.NUMBER) {
                        tokens.add(new Token(tokenType, Double.parseDouble(matchedText.trim())));
                    } else {
                        tokens.add(new Token(tokenType, matchedText.trim()));
                    }
                    remainingInput = remainingInput.substring(matchedText.length()).trim();
                    matched = true;
                    break;
                }
            }
        }

        return tokens;
    }
}
