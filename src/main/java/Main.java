import java.util.Scanner;

public class Main implements CalculatorInterface {
	private CalculatorResources resources;
	private TokenList currentInput;

	Main() {
		resources = new CalculatorResources();
		currentInput = new ConcreteTokenList();
	}

	public TokenList readTokens(String input) {
		return resources.readTokens(input);
	}


	public Double rpn(TokenList tokens) {
		return resources.RPNProcessor(tokens);
	}

	public TokenList shuntingYard(TokenList tokens) {
		return resources.shuntingYard(tokens);
	}

	private void start() {
		System.out.println(Double.NEGATIVE_INFINITY + 1);
		// Create a scanner on System.in
		Scanner consoleInput = new Scanner(System.in);

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
