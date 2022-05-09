package playground.clonetest;

import playground.builder.Pizza;

public class KPizaa extends Pizza {

    public KPizaa(Pizza.Builder<?> builder) {
        super(builder);
        super.protectedName = "df";
    }
}
