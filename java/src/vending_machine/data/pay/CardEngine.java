package vending_machine.data.pay;

import vending_machine.domain.ProductInfo;
import vending_machine.domain.Result;

public class CardEngine extends PayEngine<Card>{

    public CardEngine() {
        super(Card.class);
    }

    @Override
    public Result<Card> pay(Currency currency, ProductInfo productInfo) {
        // .... 카드 결제 과정...
        return Result.success((Card)currency);
    }
}
