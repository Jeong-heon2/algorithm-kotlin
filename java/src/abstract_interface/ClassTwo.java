package abstract_interface;

public class ClassTwo {

    protected AbstOne abstOne;
    protected String a;
    public ClassTwo (AbstOne abstOne) {
        this.abstOne = abstOne;
    }

    public void countUp() {
        abstOne.count++;
    }
    class Inner {
        public void inn() {
            String b = a;
        }
    }
}
