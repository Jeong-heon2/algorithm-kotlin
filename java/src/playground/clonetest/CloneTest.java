package playground.clonetest;

import playground.builder.Pub;

public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        CloneableClass cc = new CloneableClass();
        CloneableClass c2 = cc.clone();
        System.out.println(c2.i);
    }
    static class NonCloneableClass  {
        public int i = 1;
    }

    static class CloneableClass implements Cloneable {
        public int i = 1;
        @Override public CloneableClass clone() throws CloneNotSupportedException {
            return (CloneableClass) super.clone();
        }
    }
}
