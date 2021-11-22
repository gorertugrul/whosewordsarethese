package src.analyser;

import src.model.Ratio;
import src.parser.TextRatioParser;
import src.service.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class Analyser {
    public static List<String> analyse(String searchText, Service service) {
        Map<String, Integer> ratios =  service.writers().stream()
                .map(w -> new AbstractMap.SimpleEntry<>(w, findRatio(searchText, service.wordRatios(w))))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
        int maxRatio = ratios.values().stream().max(Integer::compareTo).orElse(0);
        return ratios.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxRatio))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static int findRatio(String searchText, Map<String, Ratio> ratios) {
        return TextRatioParser.parse(searchText).keySet().stream()
                .map(ratios::get)
                .filter(Objects::nonNull)
                .mapToInt(Ratio::getCount)
                .sum();
    }

}
