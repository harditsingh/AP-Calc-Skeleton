import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main implements CalculatorInterface {
	private CalculatorResources resources;
	private TokenList currentInput;

	Main() {
		resources = new CalculatorResources();
		currentInput = new ListToken();
	}

	public TokenList readTokens(String input) {
		return resources.readTokens(input);
	}


	public Double rpn(TokenList tokens) {
		return resources.RPNProcessor(tokens);
	}

	public TokenList shuntingYard(TokenList tokens) {
		// TODO: Implement this
		return resources.shuntingYard(tokens);
	}

	private void start() {

		// Create a scanner on System.in
		Scanner consoleInput = new Scanner(System.in);

		while (consoleInput.hasNext()) {
			String line = consoleInput.nextLine();
			
			if(line.equals("exit")) {
				break;
			}
			
			currentInput = readTokens(line);
			currentInput = shuntingYard(currentInput);
			double answer = rpn(currentInput);
			System.out.println(answer);
		}

		// While there is input, read line and parse it.
	}

	public static void main(String[] argv) {
		new Main().start();
	}
}
