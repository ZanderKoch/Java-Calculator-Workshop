package zander.lexicon.calculator;

/**
 * The {@code Token} class represents a
 */
public class Token {
    public enum TokenType {
        NUMBER, PLUS, MINUS, MULTIPLY, DIVIDE, LEFT_PAREN, RIGHT_PAREN
    };

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }


    private TokenType type;
    private String value;


    private Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public static Token createToken(TokenType type, String value){
        if (type == TokenType.NUMBER && (value == null || value.isEmpty())){
            throw new IllegalArgumentException("NUMBER type tokens must have a value");
        }
        return new Token(type, value);
    }

    public static Token createToken(TokenType type){
        if (type == TokenType.NUMBER){
            throw new IllegalArgumentException("NUMBER type tokens must have a value");
        }
        return new Token(type, null);
    }

    @Override
    public String toString(){
        return String.format("%s(%s)", type, value);
    }
}
