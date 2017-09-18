public class ConcreteDoubleStack implements DoubleStack {
    private Double[] doubleStack = new Double[0];

    @Override
    public void push(Double element) {
        int newLength = doubleStack.length + 1;
        Double[] newStack = new Double[newLength];
        for (int i = 0; i < newLength; i++) {
            if (i == (newLength - 1)) {
                newStack[i] = element;
            } else {
                newStack[i] = doubleStack[i];
            }
        }
        doubleStack = newStack;
    }

    @Override
    public Double pop() {
        int length = doubleStack.length;
        Double[] newStack = new Double[length - 1];

        Double result = 0.0;
        for (int i = 0; i < length; i++) {
            if (i == (length - 1)) {
                result = doubleStack[i];
            } else {
                newStack[i] = doubleStack[i];
            }
        }

        doubleStack = newStack;
        return result;
    }

    @Override
    public Double top() {
        return doubleStack[size() -1];
    }

    @Override
    public int size() {
        return doubleStack.length;
    }
}