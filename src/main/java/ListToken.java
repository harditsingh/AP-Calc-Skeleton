import java.util.ArrayList;

public class ListToken implements TokenList {
    public Token[] tokenArrayList = new Token[0];


    @Override
    public void add(Token token) {
        int newLength = tokenArrayList.length + 1;
        Token[] newArray = new Token[newLength];
        for (int i = 0; i < newLength; i++) {
            if (i == (newLength - 1)) {
                newArray[i] = token;
            } else {
                newArray[i] = tokenArrayList[i];
            }
        }
        tokenArrayList = newArray;
    }

    @Override
    public void remove(int index) {
        int newLength = tokenArrayList.length - 1;
        Token[] newArray = new Token[newLength];
        for (int i = 0; i < newLength; i++) {
            if (i == index) {
                i++;
                newArray[index] = tokenArrayList[i];
            } else {
                newArray[i] = tokenArrayList[i];
            }
        }
        tokenArrayList = newArray;
    }

    @Override
    public void set(int index, Token token) {
        int newLength = tokenArrayList.length + 1;
        Token[] newArray = new Token[newLength];
        for (int i = 0; i < newLength; i++) {
            if (i == index) {
                newArray[index] = token;
            } else {
                newArray[i] = tokenArrayList[i];
            }
        }
        tokenArrayList = newArray;
    }

    @Override
    public Token get(int index) {
        return tokenArrayList[index];
    }

    @Override
    public int size() {
        return tokenArrayList.length;
    }
}
