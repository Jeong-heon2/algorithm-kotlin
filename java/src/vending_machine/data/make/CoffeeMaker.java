package vending_machine.data.make;

public class CoffeeMaker implements Maker{

    String coffeeName;
    public CoffeeMaker(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    @Override
    public MakedProduct make() {
        return new Coffee(coffeeName);
    }
}
