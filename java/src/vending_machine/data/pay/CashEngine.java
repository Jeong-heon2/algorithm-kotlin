package vending_machine.data.pay;

import vending_machine.data.CashDataSource;
import vending_machine.domain.ProductInfo;
import vending_machine.domain.Result;

public class CashEngine implements PayEngine<Cash>{

    private final CashDataSource cashDataSource;

    public CashEngine(CashDataSource cashDataSource) {
        this.cashDataSource = cashDataSource;
    }

    //현금 결제 과정
    @Override
    public Result<Cash> pay(Cash currency, ProductInfo productInfo) {
        int total = productInfo.price * productInfo.count;
        int remain = total - currency.getValue();
        if (productInfo.price * productInfo.count > currency.getValue()) {
            return Result.failure(new ShortOnCashException());
        }
        cashDataSource.add(total - remain);
        return Result.success(new Cash(remain));
    }

    public static class ShortOnCashException extends Exception {
    }
}
