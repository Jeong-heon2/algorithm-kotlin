package vending_machine.presentation;

import vending_machine.data.MoneyDataSourceImpl;
import vending_machine.data.ProductDataSourceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Input {

    private static Controller controller;
    public static void main(String[] args) throws Exception{
        init();
        start();
    }

    private static void start() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("1. 상품 목록 보기  2. 구매하기  3. 상품 채우기  4. 종료");
            String input = br.readLine();
            switch (input) {
                case "1": {
                    controller.printProducts();
                    break;
                }
                case "2": {
                    System.out.println("상품명 : ");
                    String name = br.readLine();
                    System.out.println("개수 : ");
                    int count = Integer.parseInt(br.readLine());
                    System.out.println("돈 : ");
                    int pay = Integer.parseInt(br.readLine());
                    controller.buyProduct(name, count, pay);
                    break;
                }
                case "3": {
                    break;
                }
                case "4": {
                    br.close();
                    return;
                }
                default: {
                    System.out.println("올바른 입력이 아닙니다.");
                }
            }
        }
    }

    private static void init() {
        ProductDataSourceImpl productDataSource = ProductDataSourceImpl.getInstance();
        MoneyDataSourceImpl moneyDataSource = MoneyDataSourceImpl.getInstance();
        DashBoard dashBoard = new DashBoard();
        controller = new Controller(dashBoard, productDataSource, moneyDataSource);
    }
}
