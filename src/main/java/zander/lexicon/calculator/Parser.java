package zander.lexicon.calculator;

import java.util.List;

/**
 * The {@code Parser} class parses a list of {@code Token}s to construct parse tree according to the grammar
 * <pre>
 * expression = term ((‘+’ | ‘-’) term)*
 * term = factor ((‘*’ | ‘/’) factor)*
 * factor = number | ‘(’ expression ‘)’
 * </pre>
 * using recursive descent parsing, which can then be evaluated to calculate the result of the expression that the tokens represent
 */
public class Parser {
    private List<Token> tokens;
    private int index = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Object Parse(){
        index = 0;
        Node parseTree = parseExpression();
        System.out.println(parseTree);
        return parseTree.evaluate();
    }

    private Node parseExpression(){
        //expression = term ((‘+’ | ‘-’) term)*
        /*Expressions are made of a term optionally connected to a second term with ('+' | '-'),
        * so we find the first term.*/
        Node left = parseTerm();

        // Look for '+' or '-' and handle subsequent terms
        while (index < tokens.size() && matchTypes(tokens.get(index), TokenType.PLUS, TokenType.MINUS)) {
            // Get the operator
            Token operator = tokens.get(index);
            index++; // Advance past the operator

            // Parse the next term
            Node right = parseTerm();

            // Combine the current operator, left node, and right node
            left = Node.createNode(operator, left, right);
        }

        return left; // Return the constructed expression tree


    }

    private Node parseTerm(){
        //term = factor ((‘*’ | ‘/’) factor)*
        /*Terms are made of a factor optionally connected to a second factor with ('*' | '/'),
        * so we find the first factor.*/
        Node left = parseFactor();

        // If there's a '*' or '/' operator, recursively parse the rest
        if (index < tokens.size() && matchTypes(tokens.get(index), TokenType.MULTIPLY, TokenType.DIVIDE)) {
            // Get the operator
            Token operator = tokens.get(index);
            index++; // Advance past the operator

            // Parse the next term (factor and subsequent operators)
            Node right = parseTerm();

            // Combine into a single node
            return Node.createNode(operator, left, right);
        }

        return left; // If no operator, return the factor as the term
    }




    private Node parseFactor() {
        //factor = number | ‘(’ expression ‘)’
        // A factor is either a number or an expression surrounded by parentheses
        /*If we found a number then we don't have to go any deeper, and can send the number we found up
        * and advance the current token*/
        if (matchTypes(tokens.get(index), TokenType.NUMBER)){
            Token foundToken = tokens.get(index);
            index++;
            return new Node(foundToken);
        } else if (matchTypes(tokens.get(index), TokenType.LEFT_PAREN)){
            /*if it was not a number it should be an expression within parentheses,
            advance past the opening parenthesis start parsing the expression.*/
            index++;
            Node expression = parseExpression();

            //check that we haven't gone past the end of the token list or are missing a closing parenthesis
            if (index >= tokens.size() || !matchTypes(tokens.get(index), TokenType.RIGHT_PAREN )) {
                throwUnexpectedToken(tokens.get(index), TokenType.RIGHT_PAREN);
            }
            index++; //advance past the closing parenthesis

            return expression;
        } else {
            throwUnexpectedToken(tokens.get(index), TokenType.NUMBER, TokenType.LEFT_PAREN);
        }
        return null; //should never be reached, just prevents a compilation error
    }

    private boolean matchTypes(Token token, TokenType... targetTypes){
        for (TokenType targetType : targetTypes){
            if (token.getType() == targetType ){
                return true;
            }
        }
        return false;
    }

    private void throwUnexpectedToken(Token actual, TokenType firstExpected, TokenType... additionalExpected) {
        // Combine all expected TokenTypes into a single, neatly formatted string
        StringBuilder expectedTokens = new StringBuilder(firstExpected.toString());
        for (TokenType type : additionalExpected) {
            expectedTokens.append(", ").append(type);
        }

        // Throw the exception with the formatted message
        throw new InvalidInputException("Unexpected Token: " + actual + ". Expected: " + expectedTokens);
    }

}
