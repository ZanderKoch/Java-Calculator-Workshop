package zander.lexicon.calculator;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter mathematical expressions to calculate their " +
                "result or \"quit\" to exit the program");

        boolean quitting = false;
        while (!quitting) {
            try {
                String input = sc.nextLine();

                if (input.equalsIgnoreCase("quit")) {
                    quitting = true;
                    break;
                }

                if (input.isEmpty()) {
                    throw new InvalidInputException("Input cannot be empty. Please enter a valid expression.");
                }

                Tokenizer tokenizer = new Tokenizer(input);
                List<Token> output = tokenizer.tokenize();
                System.out.println(output);

                Parser parser = new Parser(output);
                System.out.println(parser.Parse());

            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
        }
    }

}