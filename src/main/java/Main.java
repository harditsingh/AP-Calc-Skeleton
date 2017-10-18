import java.util.Scanner;

public class Main implements CalculatorInterface {
	private CalculatorResources resources;
	private TokenList currentInput;

	Main() {
		resources = new CalculatorResources();
		currentInput = new ConcreteTokenList();
	}

	// Passes a new line to the resources's readTokens function to return a TokenList
	public TokenList readTokens(String input) {
		return resources.readTokens(input);
	}

	// Passes our TokenList previously filled by readTokens, to be reordered for RPN with shutingYard method
	public TokenList shuntingYard(TokenList tokens) {
		return resources.shuntingYard(tokens);
	}

	// Passes our TokenList previously reordered by shuntingYard, to perform the calculations
	public Double rpn(TokenList tokens) {
		return resources.RPNProcessor(tokens);
	}

	private void start() {
		Scanner consoleInput = new Scanner(System.in);

		// Goes through all the lines and performs and outputs calculations
		while (consoleInput.hasNext()) {
			String line = consoleInput.nextLine();

			if(line.equals("exit")) {
				break;
			}

			currentInput = readTokens(line);

			if(currentInput != null) {
				currentInput = shuntingYard(currentInput);
				double answer = rpn(currentInput);
				System.out.println(answer);
			}
		}

		consoleInput.close();
	}

	public static void main(String[] argv) {
		new Main().start();
	}
}
