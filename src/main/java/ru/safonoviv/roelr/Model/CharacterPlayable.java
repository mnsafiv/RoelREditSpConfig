package ru.safonoviv.roelr.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.safonoviv.roelr.Model.Character.CharacterPrototype;
import ru.safonoviv.roelr.Model.Stats.CalculateStats;
import ru.safonoviv.roelr.Model.Stats.DetailAttribute;
import ru.safonoviv.roelr.Model.Stats.DetailStats;

@Getter
@Setter
@NoArgsConstructor
public class CharacterPlayable {

    private String keyId;
    private DetailStats stats;
    private DetailAttribute attribute;


    public CharacterPlayable(CharacterPrototype characterPrototype, int level, CalculateStats calculateStats) {
        this.stats = calculateStats.calculateStatsByLevel(characterPrototype, level);
        this.keyId = characterPrototype.getId();
        attribute = new DetailAttribute(level,
                calculateStats.getExpByLevel(level,characterPrototype.getTierType()),
                0,
                characterPrototype.getJobType(),
                characterPrototype.getAttackType(),
                characterPrototype.getArmorType(),
                characterPrototype.getTierType());
    }
}
