package ru.safonoviv.roelr.Model.Stats;

import ru.safonoviv.roelr.Model.Enum.ArmorType;
import ru.safonoviv.roelr.Model.Enum.AttackType;
import ru.safonoviv.roelr.Model.Enum.JobType;
import ru.safonoviv.roelr.Model.Enum.TierType;

public class DetailAttribute {
    private int level;
    private int curExp;
    private int availableExp;
    private JobType jobType;
    private AttackType attackType;
    private ArmorType armorType;
    private TierType tierType;

    public DetailAttribute(int level, int curExp, int availableExp, JobType jobType, AttackType attackType, ArmorType armorType, TierType tierType) {
        this.level = level;
        this.curExp = curExp;
        this.availableExp = availableExp;
        this.jobType = jobType;
        this.attackType = attackType;
        this.armorType = armorType;
        this.tierType = tierType;
    }

    public DetailAttribute() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurExp() {
        return curExp;
    }

    public void setCurExp(int curExp) {
        this.curExp = curExp;
    }

    public int getAvailableExp() {
        return availableExp;
    }

    public void setAvailableExp(int availableExp) {
        this.availableExp = availableExp;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    public TierType getTierType() {
        return tierType;
    }

    public void setTierType(TierType tierType) {
        this.tierType = tierType;
    }
}
