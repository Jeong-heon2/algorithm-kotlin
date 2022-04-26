package covariance;

public class MyStack<T extends Animal> {
    T data;
    public T produce() {
        return data;
    }

    public void consume(T a) {
        data = a;
    }
}
