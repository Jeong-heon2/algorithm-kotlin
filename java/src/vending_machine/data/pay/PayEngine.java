package vending_machine.data.pay;

import vending_machine.domain.ProductInfo;
import vending_machine.domain.Result;

public interface PayEngine<T extends Currency> {
    // 화폐 / 구매 상품 정보
    public Result<T> pay(T currency, ProductInfo productInfo);
}
