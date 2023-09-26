package ru.safonoviv.roelr.Model;


import ru.safonoviv.roelr.Model.Character.CharacterPrototype;
import ru.safonoviv.roelr.Model.Stats.CalculateStats;
import ru.safonoviv.roelr.Model.Stats.DetailAttribute;
import ru.safonoviv.roelr.Model.Stats.DetailStats;

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

    public CharacterPlayable() {

    }

    public String getKeyId() {
        return keyId;
    }

    public DetailStats getStats() {
        return stats;
    }

    public void setStats(DetailStats stats) {
        this.stats = stats;
    }

    public DetailAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(DetailAttribute attribute) {
        this.attribute = attribute;
    }
}
