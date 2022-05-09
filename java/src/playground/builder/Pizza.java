package playground.builder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {
    String packagePrivate = "d";
    protected String protectedName = "f";
    public enum Topping { HAM, ONION }
    final Set<Topping> toppings;

    protected abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }
        abstract Pizza build();
        protected abstract T self();
    }

    public Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}
