package zander.lexicon.calculator;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("type enter mathematical expressions to calculate their " +
                "result or \"quit\" to exit the program");

        boolean quitting = false;
        while (!quitting) {
            try {
                String input = sc.nextLine();

                if (input.equalsIgnoreCase("quit")) {
                    quitting = true;
                    break;
                }

                if (Objects.equals(input, "")) {
                    throw new InvalidInputException("Input cannot be empty. Please enter a valid expression.");
                }

                //tokenize input
                //remember to add specific catches with error messages for the user!
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
        }
    }

}