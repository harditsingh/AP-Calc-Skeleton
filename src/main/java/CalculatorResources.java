import java.util.Scanner;

public class CalculatorResources {
	/*
	 * Here we create the methods for parsing, handling shunting yard, rpn and calculating the output
	 * */
	
    static final String OPERATOR_TOKENS = "+-*/^";

    public TokenList readTokens(String input) {
        Scanner in = new Scanner(input);
        ListToken result = new ListToken();


        while (in.hasNext()) {

        }

        return result;
    }
}
