package vending_machine.domain;

public class Result<T> {

    Object value;

    private Result(Object value){
        this.value = value;
    }

    public boolean isSuccess() {
        return !(value instanceof Failure);
    }

    public T getOrNull() {
        if (isSuccess()) return (T) value;
        else return null;
    }

    public Throwable exceptionOrNull() {
        if (value instanceof Failure) return ((Failure) value).exception;
        else return null;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value);
    }
    public static <T> Result<T> failure(Throwable exception) {
        return new Result<>(new Failure(exception));
    }

    static class Failure {
        Throwable exception;
        Failure(Throwable exception) {
            this.exception = exception;
        }
    }
}
