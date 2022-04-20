package vending_machine.presentation;

import vending_machine.data.CashDataSourceImpl;
import vending_machine.data.ProductDataSourceImpl;
import vending_machine.data.pay.*;

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
            System.out.println("1. 상품 목록 보기  2. 구매하기  3. 신규 상품 등록  4. 종료");
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
                    System.out.println("화폐 입력 \n 1. 현급   2. 카드");
                    String currency = br.readLine();
                    if (currency.equals("1")) {
                        System.out.println("돈 : ");
                        int pay = Integer.parseInt(br.readLine());
                        controller.buyProduct(name, count, new Cash(pay));
                    } else if (currency.equals("2")) {
                        controller.buyProduct(name, count, new Card());
                    }
                    break;
                }
                case "3": {
                    System.out.println("상품명 : ");
                    String name = br.readLine();
                    System.out.println("가격 : ");
                    int price = Integer.parseInt(br.readLine());
                    System.out.println("개수 : ");
                    int count = Integer.parseInt(br.readLine());
                    System.out.println("신원확인 여부 :  y/n");
                    String adultInput = br.readLine();
                    boolean adult = false;
                    if (adultInput.equals("y")) adult = true;
                    else adult = false;
                    controller.newProduct(name, price, count, adult);
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
        CashDataSourceImpl moneyDataSource = CashDataSourceImpl.getInstance();
        DashBoard dashBoard = new DashBoard();
        CashEngine cashEngine = new CashEngine(moneyDataSource);
        CardEngine cardEngine = new CardEngine();
        PayManager payManager = new PayManager(cashEngine, cardEngine);
        controller = new Controller(dashBoard, productDataSource, payManager);
    }
}
