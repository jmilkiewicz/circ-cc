import lombok.Data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class Finder {

    @Data
    static class Tuple2<A, B> {
        public final A a;
        public final B b;
    }

    static class IndexAndOccurrences extends Tuple2<Integer, Integer> {
        private IndexAndOccurrences(Integer index, Integer occurences) {
            super(index, occurences);
        }

        public static IndexAndOccurrences withIndexAndOccurences(Integer index, Integer occurences){
            return new IndexAndOccurrences(index, occurences);
        }

        public Integer getIndex() {
            return a;
        }

        public Integer getOccurrences() {
            return b;
        }
    }

    static Optional<Character> findFirstUniqueCharacter(String input) {
        if (input == null) return Optional.empty();
        return IntStream.range(0, input.length())
                .mapToObj(index -> new Tuple2<>(input.charAt(index), index))
                .<Map<Character, IndexAndOccurrences>>reduce(new HashMap<>(), (current, elem) -> {
                    current.merge(elem.a,
                            IndexAndOccurrences.withIndexAndOccurences(elem.b, 1),
                            (o, n) -> IndexAndOccurrences.withIndexAndOccurences(n.getIndex(), o.getOccurrences() + 1));
                    return current;
                }, (a, b) -> {
                    a.putAll(b);
                    return a;
                }).entrySet()
                .stream()
                .filter(e -> e.getValue().getOccurrences().equals(1))
                .min(Comparator.comparingInt(e -> e.getValue().getIndex()))
                .map(Map.Entry::getKey);

    }
}
