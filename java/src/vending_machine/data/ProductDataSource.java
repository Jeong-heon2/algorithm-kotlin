package vending_machine.data;

import vending_machine.domain.ProductInfo;
import vending_machine.domain.Result;

import java.util.List;

public interface ProductDataSource {

    // Result<남은 개수>
    public Result<Integer> popProduct(String name, int count);
    public Result<Integer> pushProduct(String name, int count);

    public Result<List<ProductInfo>> getProductInfoList();
    public Result<ProductInfo> getProductInfo(String name);
}
