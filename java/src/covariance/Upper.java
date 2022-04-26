package covariance;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Upper {

    public static void main(String[] args) {
        List<? extends Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<Human> humans = new ArrayList<>();
        animals = cats;
        animals = humans;
    }
}
class Animal{}
class Cat extends Animal{}
class Human extends Animal{}
