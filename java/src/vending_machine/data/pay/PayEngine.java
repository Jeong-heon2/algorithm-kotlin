package vending_machine.data.pay;

import vending_machine.domain.ProductInfo;
import vending_machine.domain.Result;

public abstract class PayEngine<T extends Currency> {

    private final Class<T> clazz;

    public PayEngine(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Class<T> getType() {
        return clazz;
    }
    // 화폐 / 구매 상품 정보
    public abstract Result<T> pay(Currency currency, ProductInfo productInfo);
}
