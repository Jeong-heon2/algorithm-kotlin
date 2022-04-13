package vending_machine.data;

import vending_machine.domain.ProductInfo;
import vending_machine.domain.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDataSourceImpl implements ProductDataSource{

    HashMap<String, ProductInfo> products = new HashMap<>();

    private ProductDataSourceImpl(){
        products.put("콜라", new ProductInfo("콜라", 1000, 10));
        products.put("사이다", new ProductInfo("사이다", 1000, 10));
        products.put("과자", new ProductInfo("과자", 1500, 15));
    }

    public static ProductDataSourceImpl getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public Result<Integer> popProduct(String name, int count) {
        if (products.containsKey(name)) {
            ProductInfo productInfo = products.get(name);
            int remain = productInfo.count - count;
            if (remain < 0) {
                return Result.failure(new OutOfProductsException());
            } else {
                productInfo.count = remain;
                return Result.success(remain);
            }
        } else {
            return Result.failure(new NonExistenceProductException());
        }
    }

    @Override
    public Result<Integer> pushProduct(String name, int count) {
        if (products.containsKey(name)) {
            ProductInfo productInfo = products.get(name);
            productInfo.count += count;
            return Result.success(productInfo.count);
        } else {
            return Result.failure(new NonExistenceProductException());
        }
    }

    @Override
    public Result<List<ProductInfo>> getProductInfoList() {
        return Result.success(new ArrayList<>(products.values()));
    }

    @Override
    public Result<ProductInfo> getProductInfo(String name) {
        if (products.containsKey(name)) return Result.success(products.get(name));
        else return Result.failure(new NonExistenceProductException());
    }

    private static class LazyHolder {
        private static final ProductDataSourceImpl INSTANCE = new ProductDataSourceImpl();
    }

    public static class NonExistenceProductException extends Exception {

    }
    public static class OutOfProductsException extends Exception {

    }
}
