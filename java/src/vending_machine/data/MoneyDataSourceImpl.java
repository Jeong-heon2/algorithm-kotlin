package vending_machine.data;

import vending_machine.domain.Result;

public class MoneyDataSourceImpl implements MoneyDataSource {

    private int money;

    private MoneyDataSourceImpl() {
        this.money = 0;
    }

    public static MoneyDataSourceImpl getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public Result<Integer> add(int value) {
        money += value;
        return Result.success(money);
    }

    static class LazyHolder {
        private static final MoneyDataSourceImpl INSTANCE = new MoneyDataSourceImpl();
    }
}
