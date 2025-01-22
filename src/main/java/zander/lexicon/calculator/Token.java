package zander.lexicon.calculator;

/**
 * The {@code Token} class represents a
 */
public class Token {
    private final TokenType type;
    private final Object value;

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", type, value);
    }

    public boolean matchTypes(TokenType... targetTypes){
        for (TokenType targetType : targetTypes){
            if (this.getType() == targetType ){
                return true;
            }
        }
        return false;
    }
}
