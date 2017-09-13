import java.util.Scanner;

public class CalculatorResources {
	/*
	 * Here we create the methods for parsing, handling shunting yard, rpn and calculating the output
	 * */

	private static final String OPERATOR_TOKENS = "+-*/^";
	private static final String PARENTHESES_TOKENS = "()";

	public TokenList readTokens(String input) {
		// Create scanner from input parameter, and declare the ListToken result
		Scanner in = new Scanner(input);
		TokenList result = new ListToken();

		// While loop that runs through the string, identifies each character, and fills the ListToken result with tokens
		while (in.hasNext()) {
			String newToken = in.next();

			if (isNumber(newToken)) {
				result.add(new TokenValue(newToken, Token.NUMBER_TYPE));
			} else if (isOperator(newToken)) {
				result.add(new TokenValue(newToken, Token.OPERATOR_TYPE, setPrecedence(newToken)));
			} else if (isParentheses(newToken)) {
				result.add(new TokenValue(newToken, Token.PARENTHESIS_TYPE));
			} else {
				System.out.println("Error identifying token type");
			}
		}

		return result;
	}


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

	private boolean isParentheses(String token) {
		if (token.charAt(0) == PARENTHESES_TOKENS.charAt(0)) {
			return true;
		} else if (token.charAt(0) == PARENTHESES_TOKENS.charAt(1)) {
			return true;
		}
		return false;
	}

	private boolean isOperator(String token) {
		for (int i = 0; i < OPERATOR_TOKENS.length(); i++) {
			if (token.charAt(0) == OPERATOR_TOKENS.charAt(i)) {
				return true;
			}
		}
		return false;
	}

	private boolean isNumber(String token) {
		Scanner in = new Scanner(token);
		return in.hasNextDouble();
	}


	public TokenList shuntingYard(TokenList tokens) {
		TokenList outputList = new ListToken();
		TokenStack operatorStack = new StackToken();

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
					operatorStack.pop(); //To pop off the left parenthesis from stack
				}
			}
		}

		while(operatorStack.size() > 0) {
			outputList.add(operatorStack.pop());
		}

		return outputList;
	}

	
	public double RPNProcessor(TokenList tokens) {
		TokenStack operationStack = new StackToken();
		
		for(int i = 0; i < tokens.size(); i++) {
		    Token currentToken = tokens.get(i);
			if(currentToken.getType() == Token.NUMBER_TYPE) {
				operationStack.push(tokens.get(i));
			}
			else if(currentToken.getType() == Token.OPERATOR_TYPE) {
				Token operand2 = operationStack.pop();
				Token operand1 = operationStack.pop();
				
				operationStack.push(calculateAnswer(operand1, operand2, tokens.get(i)));
			}
		}
		
		if(operationStack.size() == 1) {
			return Double.parseDouble(operationStack.top().getValue());
		}
		else {
			System.err.println(" Invalid input, remaining tokens on stack.");
			return 0;
		}
	}
	
	private Token calculateAnswer(Token operand1, Token operand2, Token operator) {
		double answer = 0;
		
		switch(operator.getValue().charAt(0)) {
		case '+':
			answer = Double.parseDouble(operand1.getValue()) + Double.parseDouble(operand2.getValue());
			break;
		case '-':
			answer = Double.parseDouble(operand1.getValue()) - Double.parseDouble(operand2.getValue());
			break;
		case '*':
			answer = Double.parseDouble(operand1.getValue()) * Double.parseDouble(operand2.getValue());
			break;
		case '/':
			answer = Double.parseDouble(operand1.getValue()) / Double.parseDouble(operand2.getValue());
			break;
		case '^':
			answer = Math.pow(Double.parseDouble(operand1.getValue()), Double.parseDouble(operand2.getValue()));
			break;
		}
		
		
		return new TokenValue(Double.toString(answer), Token.NUMBER_TYPE);
	}
	
}
