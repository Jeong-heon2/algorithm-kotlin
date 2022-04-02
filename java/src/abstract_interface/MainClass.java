package abstract_interface;

import java.math.BigDecimal;

public class MainClass {
    static int c;
    public static void main(String[] args) {
        int result = 2 / 0;
        System.out.println(result);
    }
}

class Outer {
    static class Inner {
        
    }
}
class Other {
    Outer.Inner oi = new Outer.Inner();
}
