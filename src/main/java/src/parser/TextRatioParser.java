package src.parser;

import src.model.Ratio;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextRatioParser {
    private static final Set<String> ignoreCases = Stream.of("ve", "de", "da", "ki", "ile", "ne", "ya", "ama", "fakat")
            .collect(Collectors.toCollection(HashSet::new));

    public static Map<String, Ratio> parse(String content) {
        return Arrays.stream(content.split(" "))
                .map(String::toLowerCase)
                .filter(word -> !ignoreCases.contains(word))
                .map(String::trim)
                .map(e -> e.replaceAll(":", ""))
                .map(e -> e.replaceAll(";", ""))
                .map(e -> e.replaceAll("\\.", ""))
                .map(e -> e.replaceAll(",", ""))
                .map(e -> e.replaceAll("!", ""))
                .map(e -> e.replaceAll("\\?", ""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .map(e -> new Ratio(e.getKey(), e.getValue().intValue()))
                .collect(Collectors.toMap(Ratio::getText, Function.identity()));
    }
}
