package vending_machine.presentation;

import vending_machine.data.ProductDataSource;
import vending_machine.data.ProductDataSourceImpl;
import vending_machine.data.pay.Cash;
import vending_machine.data.pay.Currency;
import vending_machine.data.pay.PayManager;
import vending_machine.domain.ProductInfo;
import vending_machine.domain.Result;

import java.util.List;

// todo: 신원확인이 필요하다면?
// todo: 돈을 받아서 제작해서 줘야한다면?  커피 자판기  이런거를 지원할 수 있으려면
// 이 구조에서 크게 바뀌지 않고  추가하거나 조금만 바꿔서 저 기능들을 지원하도록. 
public class Controller {

    private final DashBoard dashBoard;
    private final ProductDataSource productDataSource;
    private final PayManager payManager;

    public Controller(DashBoard dashBoard, ProductDataSource productDataSource, PayManager payManager) {
        this.dashBoard = dashBoard;
        this.productDataSource = productDataSource;
        this.payManager = payManager;
    }

    public void printProducts() {
        Result<List<ProductInfo>> res = productDataSource.getProductInfoList();
        if (res.getOrNull() != null) {
            dashBoard.printProducts(res.getOrNull());
        }
    }

    public void buyProduct(String name, int count, Currency currency) {
        Result<ProductInfo> productRes = productDataSource.getProductInfo(name);
        if (productRes.isSuccess()) {
            ProductInfo productInfo = productRes.getOrNull();
            if (productInfo != null) {
                if (count > productInfo.count) {
                    dashBoard.printMsg("재고 부족.");
                } else {
                    adultCheck(productInfo);
                    Result<? extends Currency> res = payManager.pay(currency, new ProductInfo(name, productInfo.price, count));
                    if (res.isSuccess()) {
                        productDataSource.popProduct(name, count);
                        if (res.getOrNull() instanceof Cash) {
                            dashBoard.printMsg("거스름돈 : " + ((Cash) res.getOrNull()).getValue());
                        }
                    } else {
                        dashBoard.printMsg(res.exceptionOrNull().getMessage());
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

    public void newProduct(String name, int price, int count, boolean adult) {
        productDataSource.newProduct(name, price, count, adult);
    }

    // Product 에 adult 는 나쁘지 않은데  ,  멤버십인지 아닌지 운영시간인지 아닌지 여러 조건일 수 있다는 가정. 판매할 수 있는 상태인지를 체크 하는 거로 확장
    // 여러 조건이니까 상태 값 하나로 는 불가능. pay 설계 개념을 여기에 도입할 수 있다.
    private boolean adultCheck(ProductInfo productInfo) {
        if (productInfo.adult) {
            System.out.println("--- 신원 확인 ---");
        }
        return true;
    }
}
