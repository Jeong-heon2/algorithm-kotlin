package vending_machine.data.pay;

import vending_machine.domain.ProductInfo;
import vending_machine.domain.Result;

public class CardEngine implements PayEngine<Card>{

    @Override
    public Result<Card> pay(Card currency, ProductInfo productInfo) {
        // .... 카드 결제 과정...
        return Result.success(currency);
    }
}
