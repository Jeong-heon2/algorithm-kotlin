package vending_machine.presentation;

import vending_machine.domain.ProductInfo;

import java.util.List;

public class DashBoard {

    public void printProducts(List<ProductInfo> productInfoList) {
        for (ProductInfo pi : productInfoList) {
            System.out.println("상품명: " + pi.name);
            System.out.println("가격: " + pi.price + " 원");
            System.out.println("남은 개수: " + pi.count + " 개");
            System.out.println();
        }
    }

    public void printMsg(String msg) {
        System.out.println(msg);
    }
}
