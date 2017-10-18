import java.util.Scanner;

public class CalculatorResources {
	/*
	 * Here we create the methods for parsing, handling shunting yard, rpn and calculating the output
	 * */

	private static final String OPERATOR_TOKENS = "+-*/^";
	private static final String PARENTHESES_TOKENS = "()";

	public TokenList readTokens(String input) {
		// Create scanner from input parameter, and declare the ConcreteTokenList result
		Scanner in = new Scanner(input);
		TokenList result = new ConcreteTokenList();

		// While loop that runs through the string, identifies each character, and fills the ConcreteTokenList result with tokens
		while (in.hasNext()) {
			String newToken = in.next();

			if (isNumber(newToken)) {
				result.add(new ConcreteToken(newToken, Token.NUMBER_TYPE));
			} else if (isOperator(newToken)) {
				result.add(new ConcreteToken(newToken, Token.OPERATOR_TYPE, setPrecedence(newToken)));
			} else if (isParentheses(newToken)) {
				result.add(new ConcreteToken(newToken, Token.PARENTHESIS_TYPE));
			} else {
				System.out.println("Error identifying token type");
				return null;
			}
		}

		in.close();
		return result;
	}

	// Given a token in the form of a string, it returns the precedence of this token as int
	private int setPrecedence(String token) {
		int precedence = 0;

		for (int i = 0; i < OPERATOR_TOKENS.length(); i++) {
			if(i%2 == 0) {
				precedence++;
			}
			if (token.charAt(0) == OPERATOR_TOKENS.charAt(i)) {
				return precedence;
			}
		}

		return 0;
	}

	// This method checks if the provided String's first char is either an opening or closing parenthesis or not
	private boolean isParentheses(String token) {
		if (token.charAt(0) == PARENTHESES_TOKENS.charAt(0)) {
			return true;
		} else if (token.charAt(0) == PARENTHESES_TOKENS.charAt(1)) {
			return true;
		}
		return false;
	}

	// This method checks if the provided String's first char is an operator or not
	private boolean isOperator(String token) {
		for (int i = 0; i < OPERATOR_TOKENS.length(); i++) {
			if (token.charAt(0) == OPERATOR_TOKENS.charAt(i)) {
				return true;
			}
		}
		return false;
	}

	// This method checks if the provided String's first char is a number or not
	private boolean isNumber(String token) {
		Scanner in = new Scanner(token);
		boolean temp = in.hasNextDouble();
		in.close();
		return temp;
	}

	// Determine each token's type and fill up the outputList so that it's ready for RPN
	public TokenList shuntingYard(TokenList tokens) {
		TokenList outputList = new ConcreteTokenList();
		TokenStack operatorStack = new ConcreteTokenStack();

		for(int i = 0; i < tokens.size(); i++) {
			if(tokens.get(i).getType() == Token.NUMBER_TYPE) {
				outputList.add(tokens.get(i));
			}
			else if(tokens.get(i).getType() == Token.OPERATOR_TYPE) {
				while(operatorStack.size() > 0) {
					if(operatorStack.top().getPrecedence() >= tokens.get(i).getPrecedence()) {
						outputList.add(operatorStack.pop());
					}
					else {
						break;
					}
				}
				operatorStack.push(tokens.get(i));
			}
			if(tokens.get(i).getType() == Token.PARENTHESIS_TYPE) {
				if(tokens.get(i).getValue().equals("(")) {
					operatorStack.push(tokens.get(i));
				}
				else if(tokens.get(i).getValue().equals(")")) {
					while(!operatorStack.top().getValue().equals("(")) {
						outputList.add(operatorStack.pop());
					}
					operatorStack.pop();
				}
			}
		}

		while(operatorStack.size() > 0) {
			outputList.add(operatorStack.pop());
		}

		return outputList;
	}


	// Using a DoubleStack for RPN, perform the calculations in the TokenList
	public double RPNProcessor(TokenList tokens) {
		DoubleStack operationStack = new ConcreteDoubleStack();

		for(int i = 0; i < tokens.size(); i++) {
		    Token currentToken = tokens.get(i);
			if(currentToken.getType() == Token.NUMBER_TYPE) {
				operationStack.push(Double.parseDouble(tokens.get(i).getValue()));
			}
			else if(currentToken.getType() == Token.OPERATOR_TYPE) {
				double operand2 = operationStack.pop();
				double operand1 = operationStack.pop();
				
				operationStack.push(calculateAnswer(operand1, operand2, tokens.get(i)));
			}
		}

		// Our result should be the last token left on the stack
		if(operationStack.size() == 1) {
			return operationStack.top();
		}
		else {
			System.err.println(" Invalid input, remaining tokens on stack.");
			return 0;
		}
	}

	// Performs +, -, *, or / calculations, given two operands and returns answer
	private double calculateAnswer(double operand1, double operand2, Token operator) {
		double answer = 0;
		
		switch(operator.getValue().charAt(0)) {
		case '+':
			answer = operand1 + operand2;
			break;
		case '-':
			answer = operand1 - operand2;
			break;
		case '*':
			answer = operand1 * operand2;
			break;
		case '/':
			answer = operand1 / operand2;
			break;
		case '^':
			answer = Math.pow(operand1, operand2);
			break;
		}
		return answer;
	}
	
}
