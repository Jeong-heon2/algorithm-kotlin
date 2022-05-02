package vending_machine.data.pay;

import vending_machine.data.CashDataSource;
import vending_machine.domain.ProductInfo;
import vending_machine.domain.Result;

public class CashEngine extends PayEngine<Cash>{

    private final CashDataSource cashDataSource;

    public CashEngine(CashDataSource cashDataSource) {
        super(Cash.class);
        this.cashDataSource = cashDataSource;
    }

    //현금 결제 과정
    @Override
    public Result<Cash> pay(Currency currency, ProductInfo productInfo) {
        Cash cash = (Cash) currency;
        int total = productInfo.price * productInfo.count;
        int remain = total - cash.getValue();
        if (productInfo.price * productInfo.count > cash.getValue()) {
            return Result.failure(new ShortOnCashException());
        }
        cashDataSource.add(total - remain);
        return Result.success(new Cash(remain));
    }

    public static class ShortOnCashException extends Exception {
    }
}
