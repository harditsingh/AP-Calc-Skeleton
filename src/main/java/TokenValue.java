
public class TokenValue implements Token {
	
	private int tokenType;
	private String tokenValue;
	
	TokenValue(String value, int type) {
		tokenValue = value;
		tokenType = type;
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
		// TODO Auto-generated method stub
		return 0;
	}
}
