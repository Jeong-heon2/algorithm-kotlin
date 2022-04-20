package vending_machine.data;

import vending_machine.domain.Result;

// todo: 카드를 받는다?  돈이아닌 토큰을 받는다?
public class CashDataSourceImpl implements CashDataSource {

    private int money;

    private CashDataSourceImpl() {
        this.money = 0;
    }

    public static CashDataSourceImpl getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public Result<Integer> add(int value) {
        money += value;
        return Result.success(money);
    }

    static class LazyHolder {
        private static final CashDataSourceImpl INSTANCE = new CashDataSourceImpl();
    }
}
