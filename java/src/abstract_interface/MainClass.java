package abstract_interface;

public class MainClass {
    public static void main(String[] args) {
        ClassTwo c2 = new ClassTwo(new ClassOne());

        System.out.println(c2.abstOne.count);
    }
}
