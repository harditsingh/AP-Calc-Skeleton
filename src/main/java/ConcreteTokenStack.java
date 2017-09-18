public class ConcreteTokenStack implements TokenStack{
	private Token[] tokenStack = new Token[0];

	@Override
	public void push(Token token) {
		int newLength = tokenStack.length + 1;
		Token[] newStack = new Token[newLength];
		for (int i = 0; i < newLength; i++) {
			if (i == (newLength - 1)) {
				newStack[i] = token;
			} else {
				newStack[i] = tokenStack[i];
			}
		}
		tokenStack = newStack;
	}

	@Override
	public Token pop() {
		int newLength = tokenStack.length;
		Token[] newStack = new Token[newLength - 1];

		Token result = null;
		for (int i = 0; i < newLength; i++) {
			if (i == (newLength - 1)) {
				result = tokenStack[i];
			} else {
				newStack[i] = tokenStack[i];
			}
		}

		tokenStack = newStack;
		return result;
	}

	@Override
	public Token top() {
		return tokenStack[size() - 1];
	}

	@Override
	public int size() {
		return tokenStack.length;
	}
}
