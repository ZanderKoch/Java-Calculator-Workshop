package zander.lexicon.calculator;

import java.util.ArrayList;
import java.util.List;

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

        while(position < input.length()){
            char current = input.charAt(position);

            if (Character.isWhitespace(current)){
                position++;
            } else if (Character.isDigit(current)){
                //
            }

        }

        return tokens;
    }
}
