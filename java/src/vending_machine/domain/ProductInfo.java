package vending_machine.domain;

import java.util.Objects;

public class ProductInfo {
    public String name;
    public int price;
    public int count;

    public ProductInfo(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfo productInfo = (ProductInfo) o;
        return name.equals(productInfo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
