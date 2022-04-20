package vending_machine.data;

import vending_machine.domain.Result;

public interface CashDataSource {
    // Result<남은 돈>
    public Result<Integer> add(int value);
}
