package vending_machine.data.pay;

import vending_machine.domain.ProductInfo;
import vending_machine.domain.Result;

public class PayManager {

    private final CashEngine cashEngine;
    private final CardEngine cardEngine;

    public PayManager(CashEngine cashEngine, CardEngine cardEngine){
        this.cashEngine = cashEngine;
        this.cardEngine = cardEngine;
    }

    public Result<? extends Currency> pay(Currency currency, ProductInfo productInfo) {
        if (currency instanceof Cash) {
            return cashEngine.pay((Cash) currency, productInfo);
        } else if (currency instanceof Card) {
            return cardEngine.pay((Card) currency, productInfo);
        } else {
            return Result.failure(new Exception("존재 하지 않는 화폐 수단"));
        }
    }
}
