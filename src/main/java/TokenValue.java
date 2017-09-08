
public class TokenValue implements Token {
	
	private int tokenType;
	private String tokenValue;
	private int tokenPrecedence;
	
	TokenValue(String value, int type) {
		tokenValue = value;
		tokenType = type;
		tokenPrecedence = 0;
	}
	
	TokenValue(String value, int type, int precedence) {
		tokenValue = value;
		tokenType = type;
		tokenPrecedence = precedence;
	}
	
	@Override
	public String getValue() {
		return tokenValue;
	}

	@Override
	public int getType() {
		return tokenType;
	}

	@Override
	public int getPrecedence() {
		return tokenPrecedence;
	}
}
