package zander.lexicon.calculator;

public class Node {

    private Token token;
    private Node left;
    private Node right;

    public Node(Token token) {
        this.token = token;
    }

    public static Node createNode(Token token, Node left, Node right){
        Node node = new Node(token);
        node.left = left;
        node.right = right;
        return node;
    }

    public Token getToken() {
        return token;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Object evaluate() {
        // Use the evaluate function from TokenType enum to evaluate this node
        return token.getType().getEvaluate().apply(this);
    }

    @Override
    public String toString() {
        if (token.getType() == TokenType.NUMBER) {
            return token.getValue().toString();  // For numbers, just return the value
        } else if (token.getType() == TokenType.PLUS || token.getType() == TokenType.MINUS ||
                token.getType() == TokenType.MULTIPLY || token.getType() == TokenType.DIVIDE) {
            // If it's an operator, print the left and right child (subexpressions)
            return "(" + left.toString() + " " + token.getValue() + " " + right.toString() + ")";
        }
        return "";
    }
}


