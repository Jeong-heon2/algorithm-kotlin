package playground.nested;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class TestMani {
    static int staticField = 1;
    int nonStaticField = 2;
    public static <E> void swap(List<E> list, int i , int j) {
        list.set(i, list.set(j, list.get(i)));
    }
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        swap(a, 0, 1);
        Anonymous anonymous = new Anonymous() {
            int a = 1;
            //static int b = 2; static field 안 됨
            @Override
            public void func() {
                //...
                a = 2;
                staticField = 2;
            }
        };

        StaticNestedAnonymous staticNestedAnonymous = new StaticNestedAnonymous() {
            @Override
            public void func1() {
                //;;;
                staticField = 3;
            }
        };
    }

    public static void staticMethod() {
        StaticNestedAnonymous staticNestedAnonymous = new StaticNestedAnonymous() {
            @Override
            public void func1() {
                //;;;
                staticField = 3;
                //nonStaticField = 2; // 정적 문맥에서는 사용할 수 없다.
            }
        };
    }

    static abstract class StaticNestedAnonymous {
        public abstract void func1();
    }
}
