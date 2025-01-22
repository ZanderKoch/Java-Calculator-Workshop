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

            System.out.println(remainingInput);
            for (TokenType tokenType : TokenType.values()) {
                Matcher matcher = tokenType.getPattern().matcher(remainingInput);
                if (matcher.lookingAt()) {
                    String matchedText = matcher.group();
                    //special handling for numbers
                    if (tokenType == TokenType.NUMBER) {
                        if (negativeNumberEligible(tokens)){
                            tokens.remove(tokens.size() - 1);
                            tokens.add(new Token(tokenType, Double.parseDouble(matchedText.trim()) * -1));
                        } else {
                            tokens.add(new Token(tokenType, Double.parseDouble(matchedText.trim())));
                        }


                    //all other cases
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

    private boolean negativeNumberEligible(List<Token> tokenList) {
        if (tokenList.size() == 1 && tokenList.getFirst().getType() == TokenType.MINUS) {
            return true;
        }

        if (tokenList.size() >= 2) {
            Token lastToken = tokenList.getLast();
            Token secondLastToken = tokenList.get(tokenList.size() - 2);
            if (lastToken.getType() == TokenType.MINUS &&
                    !secondLastToken.matchTypes(TokenType.NUMBER, TokenType.RIGHT_PAREN)) {
                return true;
            }
        }
        return false;
    }
}
