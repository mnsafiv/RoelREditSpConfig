package ru.safonoviv.roelr.Model.Character;


import org.springframework.beans.factory.annotation.Autowired;
import ru.safonoviv.roelr.CharacterPrototypeConfig;
import ru.safonoviv.roelr.Model.Enum.*;
import ru.safonoviv.roelr.Model.Skill.CharacterSkill;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


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

    public CharacterPrototype() {
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


    public double getHpBase() {
        return hpBase;
    }

    public void setHpBase(double hpBase) {
        this.hpBase = hpBase;
    }

    public double getAtkBase() {
        return atkBase;
    }

    public void setAtkBase(double atkBase) {
        this.atkBase = atkBase;
    }

    public double getDefBase() {
        return defBase;
    }

    public void setDefBase(double defBase) {
        this.defBase = defBase;
    }

    public double getSpdBase() {
        return spdBase;
    }

    public void setSpdBase(double spdBase) {
        this.spdBase = spdBase;
    }

    public double getHpProgress() {
        return hpProgress;
    }

    public void setHpProgress(double hpProgress) {
        this.hpProgress = hpProgress;
    }

    public double getAtkProgress() {
        return atkProgress;
    }

    public void setAtkProgress(double atkProgress) {
        this.atkProgress = atkProgress;
    }

    public double getDefProgress() {
        return defProgress;
    }

    public void setDefProgress(double defProgress) {
        this.defProgress = defProgress;
    }

    public double getSpdProgress() {
        return spdProgress;
    }

    public void setSpdProgress(double spdProgress) {
        this.spdProgress = spdProgress;
    }

    public List<CharacterSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<CharacterSkill> skills) {
        this.skills = skills;
    }

    public TierType getTierType() {
        return tierType;
    }

    public void setTierType(TierType tierType) {
        this.tierType = tierType;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }
}
