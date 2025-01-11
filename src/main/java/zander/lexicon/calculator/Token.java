package zander.lexicon.calculator;

/**
 * The {@code Token} class represents a
 */
public class Token {
    public enum TokenType {
        NUMBER, PLUS, MINUS, MULTIPLY, DIVIDE, LEFT_PARAN, RIGHT_PARAN
    };

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }


    private TokenType type;
    private String value;


    private Token(TokenType type) {
        this.type = type;

    }

    @Override
    public String toString(){
        return String.format("%s(%s)", type, value);
    }
}
