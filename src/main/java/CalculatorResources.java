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
        ListToken result = new ListToken();

        // While loop that runs through the string, identifies each character, and fills the ListToken result with tokens
        while (in.hasNext()) {
            String newToken = in.next();

            if (isNumber(newToken)) {
                result.add(new TokenValue(newToken, Token.NUMBER_TYPE));
            } else if (isOperator(newToken)) {
                result.add(new TokenValue(newToken, Token.OPERATOR_TYPE));
            } else if (isParentheses(newToken)) {
                result.add(new TokenValue(newToken, Token.PARENTHESIS_TYPE));
            } else {
                System.out.println("Error identifying token type");
            }
        }

        return result;
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


}
