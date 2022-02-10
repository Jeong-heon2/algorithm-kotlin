package abstract_interface;

public class ClassOne extends AbstOne implements InterOne{

    protected int count = 10;

    @Override
    public void countUp() {
        count++;
    }
}
