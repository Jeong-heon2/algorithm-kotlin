package playground.enummap;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class Test {

    public enum Phase {
        SOLID, LIQUID, GAS;

        public enum Transition {
            MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
            BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
            SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

            private final Phase from;
            private final Phase to;

            Transition(Phase from, Phase to) {
                this.from = from;
                this.to = to;
            }

            private static final Map<Phase, Map<Phase, Transition>> m =
                    Stream.of(values()).collect(
                            groupingBy( // 첫번째 Collector
                                    t -> t.from, () -> new EnumMap<>(Phase.class),
                                    toMap(t -> t.to, t -> t, (x,y) -> y, () -> new EnumMap<>(Phase.class)) // 두번째 Collector
                            )
                    );
        }
    }
}
