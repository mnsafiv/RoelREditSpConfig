package ru.safonoviv.roelr.Model.Character;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.safonoviv.roelr.CharacterPrototypeConfig;
import ru.safonoviv.roelr.Model.Enum.*;
import ru.safonoviv.roelr.Model.Skill.CharacterSkill;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public abstract class CharacterPrototype {
    private double hpBase;
    protected double atkBase;
    protected double defBase;
    protected double spdBase;
    protected double hpProgress;
    protected double atkProgress;
    protected double defProgress;
    protected double spdProgress;
    protected List<CharacterSkill> skills;
    protected TierType tierType;
    protected AttackType attackType;
    protected JobType jobType;
    protected ArmorType armorType;

    public CharacterPrototype(Map<String, Double> stats, List<CharacterSkill> skills, Map<String, String> attribute, EnumConfig enumConfig, CharacterPrototypeConfig characterPrototype) {
        characterPrototype.addCharacter(getId(),this);
        this.skills = skills;

        final Map<String, ? extends Enum> attributes = enumConfig.getAttribute(attribute);
        Arrays.stream(CharacterPrototype.class.getDeclaredFields()).forEach(t -> {
            final Enum anEnum = attributes.get(t.getGenericType().getTypeName());
            if (anEnum != null) {
                try {
                    t.set(this, anEnum);
                } catch (Exception e) {
                    try {
                        throw e.fillInStackTrace();
                    } catch (Throwable ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else {
                final Double fieldValue = stats.get(t.getName());
                if (fieldValue != null) {
                    try {
                        t.set(this, fieldValue);
                    } catch (Exception e) {
                        try {
                            throw e.fillInStackTrace();
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

            }

        });

    }


    @Autowired
    public abstract String getId();

    @Override
    public String toString() {
        return getId() +
                "{skills=" + skills +
                ", hpBase=" + hpBase +
                ", atkBase=" + atkBase +
                ", defBase=" + defBase +
                ", spdBase=" + spdBase +
                ", hpProgress=" + hpProgress +
                ", atkProgress=" + atkProgress +
                ", defProgress=" + defProgress +
                ", spdProgress=" + spdProgress +
                ", characterTierType=" + tierType +
                ", attackType=" + attackType +
                ", jobType=" + jobType +
                '}';
    }

}
