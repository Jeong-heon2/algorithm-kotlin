package vending_machine.presentation;

import vending_machine.data.MoneyDataSource;
import vending_machine.data.ProductDataSource;
import vending_machine.data.ProductDataSourceImpl;
import vending_machine.domain.ProductInfo;
import vending_machine.domain.Result;

import java.util.List;

public class Controller {

    private final DashBoard dashBoard;
    private final ProductDataSource productDataSource;
    private final MoneyDataSource moneyDataSource;

    public Controller(DashBoard dashBoard, ProductDataSource productDataSource, MoneyDataSource moneyDataSource) {
        this.dashBoard = dashBoard;
        this.productDataSource = productDataSource;
        this.moneyDataSource = moneyDataSource;
    }

    public void printProducts() {
        Result<List<ProductInfo>> res = productDataSource.getProductInfoList();
        if (res.getOrNull() != null) {
            dashBoard.printProducts(res.getOrNull());
        }
    }

    public void buyProduct(String name, int count, int pay) {
        Result<ProductInfo> productRes = productDataSource.getProductInfo(name);
        if (productRes.isSuccess()) {
            ProductInfo productInfo = productRes.getOrNull();
            if (productInfo != null) {
                if (count > productInfo.count) {
                    dashBoard.printMsg("재고 부족.");
                } else {
                    int total = count * productInfo.price;
                    if (total > pay) dashBoard.printMsg("돈이 모자랍니다. ");
                    else {
                        productDataSource.popProduct(name, count);
                        moneyDataSource.add(total);
                        dashBoard.printMsg("거스름돈 : " + (pay - total));
                    }
                }
            }
        } else {
            if (productRes.exceptionOrNull() instanceof ProductDataSourceImpl.NonExistenceProductException) {
                dashBoard.printMsg("존재하지 않는 상품입니다. ");
            } else {
                dashBoard.printMsg("알 수 없는 에러");
            }
        }
    }
}
