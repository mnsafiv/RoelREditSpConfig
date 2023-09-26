package ru.safonoviv.roelr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.safonoviv.roelr.Model.Character.*;
import ru.safonoviv.roelr.Model.CharacterPlayable;
import ru.safonoviv.roelr.Model.Enum.EnumConfig;
import ru.safonoviv.roelr.Model.Stats.CalculateStats;

import java.util.List;
import java.util.Map;

@Component
public class CharacterPrototypeApp {
    @Autowired
    private SkillSelector skillSelector;
    @Autowired
    private EnumConfig enumConfig;
    @Autowired
    private CalculateStats calculateStats;

    @Autowired
    private CharacterPrototypeConfig characterPrototype;






    @Bean
    public CharacterPrototype archer(@Value("#{${characterPrototype.archer.stats}}") Map<String, Double> stats,
                                     @Value("#{'${characterPrototype.archer.skill}'.split(',')}") List<String> skillKeys,
                                     @Value("#{${characterPrototype.archer.attribute}}") Map<String, String> attribute) {
        return new Archer(stats, skillSelector.getSkill(skillKeys), attribute, enumConfig,characterPrototype);
    }

    @Bean
    public CharacterPrototype cup(@Value("#{${characterPrototype.cup.stats}}") Map<String, Double> stats,
                                  @Value("#{'${characterPrototype.cup.skill}'.split(',')}") List<String> skillKeys,
                                  @Value("#{${characterPrototype.cup.attribute}}") Map<String, String> attribute) {
        return new Cup(stats, skillSelector.getSkill(skillKeys), attribute, enumConfig,characterPrototype);
    }

    @Bean
    public CharacterPrototype warrior(@Value("#{${characterPrototype.warrior.stats}}") Map<String, Double> stats,
                                      @Value("#{'${characterPrototype.warrior.skill}'.split(',')}") List<String> skillKeys,
                                      @Value("#{${characterPrototype.warrior.attribute}}") Map<String, String> attribute) {
        return new Warrior(stats, skillSelector.getSkill(skillKeys), attribute, enumConfig,characterPrototype);
    }

    @Bean
    public CharacterPrototype mage(@Value("#{${characterPrototype.mage.stats}}") Map<String, Double> stats,
                                   @Value("#{'${characterPrototype.mage.skill}'.split(',')}") List<String> skillKeys,
                                   @Value("#{${characterPrototype.mage.attribute}}") Map<String, String> attribute) {
        return new Mage(stats, skillSelector.getSkill(skillKeys), attribute, enumConfig,characterPrototype);
    }

    @Bean
    @Scope(value = "prototype")
    public CharacterPlayable getCharacterPrototype(CharacterPrototype characterPrototype, int level) {
        return new CharacterPlayable(characterPrototype, level, calculateStats);
    }

    @Bean
    @Scope(value = "prototype")
    public CharacterPlayable getCharacterPrototype(String name, int level) {
        return new CharacterPlayable(characterPrototype.get(name), level, calculateStats);
    }


}
