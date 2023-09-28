package ru.safonoviv.roelr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.safonoviv.roelr.Model.Stats.MultiplierBonus;

import java.util.*;

@Component
public class MultiplierApp {
    private final Map<String, MultiplierBonus> maps = new HashMap<>();


    @Bean
    public Set<MultiplierBonus> job(@Value("#{${character.job.range}}") Map<String, Double> range) {
        return new HashSet<>(List.of(addBonus(range, "range")));
    }

    @Bean
    public Set<MultiplierBonus> attackType(@Value("#{${character.attackType.pure}}") Map<String, Double> pure,
                                           @Value("#{${character.attackType.physical}}") Map<String, Double> physical,
                                           @Value("#{${character.attackType.magical}}") Map<String, Double> magical) {
        return new HashSet<>(Arrays.asList(addBonus(pure, "pure"), addBonus(physical, "physical"), addBonus(magical, "magical")));
    }

    @Bean
    public Set<MultiplierBonus> armorType(@Value("#{${character.armorType.light}}") Map<String, Double> light,
                                          @Value("#{${character.armorType.medium}}") Map<String, Double> medium,
                                          @Value("#{${character.armorType.heavy}}") Map<String, Double> heavy) {
        return new HashSet<>(Arrays.asList(addBonus(light, "light"), addBonus(medium, "medium"), addBonus(heavy, "heavy")));
    }

    @Bean
    public Set<MultiplierBonus> tierType(@Value("#{${character.tierType.common}}") Map<String, Double> common,
                                         @Value("#{${character.tierType.elite}}") Map<String, Double> elite,
                                         @Value("#{${character.tierType.hero}}") Map<String, Double> hero) {
        return new HashSet<>(Arrays.asList(addBonus(common, "common"), addBonus(elite, "elite"), addBonus(hero, "hero")));
    }


    public MultiplierBonus addBonus(Map<String, Double> bonuses, String name) {
        MultiplierBonus multiplierBonus = new MultiplierBonus(bonuses);
        MultiplierBonus put = maps.put(name, multiplierBonus);
        if (put != null) {
            throw new IllegalArgumentException("duplicate parameter bonus");
        }
        return multiplierBonus;
    }


    public MultiplierBonus getBonus(String key) {
        MultiplierBonus multiplierBonus = maps.get(key);
        if (multiplierBonus == null) {
            throw new IllegalArgumentException("not contains key: " + key);
        }
        return multiplierBonus;
    }
}
