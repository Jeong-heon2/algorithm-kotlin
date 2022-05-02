package vending_machine.data.pay;

import vending_machine.domain.ProductInfo;
import vending_machine.domain.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 자판기를 납품 하는 입장.
// 새로운 엔진이 추가 되면 새로 빌드해야 함.  매니저 빌드 없이 유연한 설계가 가능함..
// 맴버 변수로 엔진을 안 둬도 가능할 거 같다.
public class PayManager {

//    private final CashEngine cashEngine;
//    private final CardEngine cardEngine;
//
//    public PayManager(CashEngine cashEngine, CardEngine cardEngine){
//        this.cashEngine = cashEngine;
//        this.cardEngine = cardEngine;
//    }

    private final List<PayEngine<? extends Currency>> engineList = new ArrayList<>();

    public void addEngine(PayEngine<? extends Currency> engine) {
        engineList.add(engine);
    }

    public Result<? extends Currency> pay(Currency currency, ProductInfo productInfo) {
        for (PayEngine<? extends Currency> payEngine : engineList) {
            if (payEngine.getType() == currency.getClass()) {
                return payEngine.pay(currency, productInfo);
            }
        }
        return Result.failure(new Exception("존재 하지 않는 화폐 수단"));
//        if (currency instanceof Cash) {
//            return cashEngine.pay((Cash) currency, productInfo);
//        } else if (currency instanceof Card) {
//            return cardEngine.pay((Card) currency, productInfo);
//        } else {
//            return Result.failure(new Exception("존재 하지 않는 화폐 수단"));
//        }
    }
}
