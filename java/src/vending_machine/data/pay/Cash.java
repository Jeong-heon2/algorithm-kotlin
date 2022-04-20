package vending_machine.data.pay;

public class Cash implements Currency{

    private final int value;

    public Cash(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Cash plus(Cash cash) {
        return new Cash(value + cash.value);
    }

    public Cash minus(Cash cash) {
        return new Cash(value - cash.value);
    }

    public Cash mul(Cash cash) {
        return new Cash(value * cash.value);
    }
}
