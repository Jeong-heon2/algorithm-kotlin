package playground;


import java.lang.invoke.*;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.spi.ToolProvider;

public class StringConcatFactoryTest {

    public static void main(String[] args) throws Throwable {
        String time = "";
        switch (LocalTime.now().get(ChronoField.HOUR_OF_DAY) / 6) {
            case 0 :{
                time = "night";
                break;
            }
            case 1 : {
                time = "morning";
                break;
            }
            case 2 : {
                time = "afternoon";
                break;
            }
            case 3 : {
                time = "evening";
                break;
            }
            default : throw new AssertionError();
        };

        //System.out.println("Hello "+System.getProperty("user.name")+", good "+time+"!");

        //String tmp = "prefix \1 "+time+" \2 suffix";

        invokeManually(time);

        //showBytecode();
    }

    private static void invokeManually(String time) throws Throwable {
        MethodHandle mh = StringConcatFactory.makeConcatWithConstants(
                MethodHandles.lookup(), // normally provided by the JVM
                "foobar", // normally provided by javac, but meaningless here
                // method type is normally provided by the JVM and matches the invocation
                MethodType.methodType(String.class, String.class),
                "Hello \2, good \1!", // recipe, \1 binds a parameter, \2 a constant
                System.getProperty("user.name") // the first constant to bind
        ).getTarget();

        // we can now use the handle to perform a concatenation
        // the argument types must match the MethodType specified above
        String result = (String)mh.invokeExact(time);

        System.out.println(result);
    }

    private static void showBytecode() {
        System.out.println();
        ToolProvider.findFirst("javap")
                .ifPresent(tp -> tp.run(System.out, System.err, "-v", "-c", StringConcatFactoryTest.class.getName()));
    }
}
