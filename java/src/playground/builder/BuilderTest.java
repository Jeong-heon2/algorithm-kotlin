package playground.builder;

public class BuilderTest {
    public static void main(String[] args) {
        NyPizza nyPizza = new NyPizza.Builder(NyPizza.Size.LARGE).addTopping(Pizza.Topping.HAM).build();
    }
}
