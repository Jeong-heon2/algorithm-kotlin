package covariance;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Upper {

    public static void main(String[] args) {
        MyStack<Cat> stack = new MyStack<>();
        Cat cat = new Cat();
        Class<Cat> clazz = Cat.class;
        if (cat.getClass() == clazz) {

        }
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
