package vending_machine.data;

import vending_machine.domain.Result;

public interface MoneyDataSource {
    // Result<남은 돈>
    public Result<Integer> add(int value);
}
