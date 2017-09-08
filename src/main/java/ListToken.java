import java.util.ArrayList;

public class ListToken implements TokenList {
    private ArrayList<Token> tokenArrayList;

    public ListToken () {
        tokenArrayList = new ArrayList<>();
    }

    @Override
    public void add(Token token) {
        tokenArrayList.add(token);
    }

    @Override
    public void remove(int index) {
        tokenArrayList.remove(index);
    }

    @Override
    public void set(int index, Token token) {
        tokenArrayList.add(index, token);
    }

    @Override
    public Token get(int index) {
        return tokenArrayList.get(index);
    }

    @Override
    public int size() {
        return tokenArrayList.size();
    }
}
