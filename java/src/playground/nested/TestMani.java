package playground.nested;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class TestMani {

    public static void main(String args[]){
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit((Runnable) System.out::println);
    }



    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }

    static Stream<BigInteger> primes() {
        return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
    }
}
