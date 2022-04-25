package vending_machine.data.make;

// 좀 더 단순화 ->  값일 지불하면 재고를 준다.  재고를 하나만 주는게 아니다.
// 커피 -> 캔커피면 캔커피하나만 나오면 된다.  믹스커피 -> 상품으로 뜨거운물 종이컵 커피 콩 을 주는 느낌
// 과정은 나중에 
public interface Maker {
    public MakedProduct make();
}
