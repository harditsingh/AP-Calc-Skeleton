import java.util.Stack;

public class StackToken implements TokenStack{
	Stack <Token> tokenStack = new Stack<Token>();

	@Override
	public void push(Token token) {
		tokenStack.push(token);
		
	}

	@Override
	public Token pop() {
		return tokenStack.pop();
	}

	@Override
	public Token top() {
		// TODO Auto-generated method stub
		return tokenStack.peek();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return tokenStack.size();
	}
}
