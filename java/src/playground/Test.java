package playground;

import java.util.Comparator;
import java.util.TreeSet;

public class Test {

    static TreeSet<Person> set = new TreeSet<>(new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            if (o1.equals(o2)) return 0;
            if (o1.cnt > o2.cnt) {
                return 1;
            } else if (o1.cnt == o2.cnt) {
                if (o1.distance > o2.distance) {
                    return 1;
                } else if (o1.distance == o2.distance) {
                    return o1.name.compareTo(o2.name);
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
    });
    public static void main(String... args) {

        set.add(new Person("Guard", 3));
        set.add(new Person("Guard", 4));
        set.remove(new Person("Guard", 0));
        if (set.contains(new Person("Guard", 0))) {
            System.out.println("contains");
        }
    }

    static class Person {
        String name;
        int distance;
        int cnt = 1;

        public Person(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o1) {
            if (o1 instanceof Person) {
                Person p = (Person) o1;
                return name.equals(p.name);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }
}
