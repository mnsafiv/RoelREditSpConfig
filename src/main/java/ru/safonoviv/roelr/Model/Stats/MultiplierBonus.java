package ru.safonoviv.roelr.Model.Stats;

import java.util.*;
import java.util.stream.Collectors;

public class MultiplierBonus {
    private final Map<String, Double> bonuses;

    public MultiplierBonus(Map<String, Double> bonuses) {
        Set<String> bonusNames = Arrays.stream(Bonus.values()).map(String::valueOf).collect(Collectors.toSet());
        bonuses.forEach((key, value) -> {
            if (!bonusNames.contains(key)) {
                throw new IllegalArgumentException(key);
            }
        });
        this.bonuses = bonuses;

    }

    public Map<String, Double> getBonuses() {
        return bonuses;
    }
}
