import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main implements CalculatorInterface {
    private CalculatorResources resources;

    Main() {
    	resources = new CalculatorResources();
    }

    public TokenList readTokens(String input) {
        // TODO: Implement this
        resources.readTokens(input);
        return null;
    }


    public Double rpn(TokenList tokens) {
        // TODO: Implement this
        return null;
    }

    public TokenList shuntingYard(TokenList tokens) {
        // TODO: Implement this
        return null;
    }

    private void start() {
        
        // Create a scanner on System.in
        Scanner consoleInput = new Scanner(System.in);

        while (consoleInput.hasNext()) {
            String line = consoleInput.nextLine();
            readTokens(line);
        }
        
        // While there is input, read line and parse it.
    }

    public static void main(String[] argv) {
    	System.out.print("Yo this works!");
    	//ABC
        new Main().start();
    }
}
