package ru.safonoviv.roelr.Model.Stats;


import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;


public class DetailStats implements Cloneable, Serializable {
    private int health;
    private int damagePhysical;
    private int damageMagic;
    private int damagePure;
    private int defencePhysical;
    private int defenceMagic;
    private int activePoint;
    private int skillPoint;
    private int movePoint;
    private int skillDistance;
    private int moveCost;
    private int skillCost;
    private int moveSpeed;
    private int skillCast;

    public int getHealth() {
        return health;
    }

    public int getDamagePhysical() {
        return damagePhysical;
    }

    public int getDamageMagic() {
        return damageMagic;
    }

    public int getDamagePure() {
        return damagePure;
    }

    public int getDefencePhysical() {
        return defencePhysical;
    }

    public int getDefenceMagic() {
        return defenceMagic;
    }

    public int getActivePoint() {
        return activePoint;
    }

    public int getSkillPoint() {
        return skillPoint;
    }

    public int getMovePoint() {
        return movePoint;
    }

    public int getSkillDistance() {
        return skillDistance;
    }

    public int getMoveCost() {
        return moveCost;
    }

    public int getSkillCost() {
        return skillCost;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public int getSkillCast() {
        return skillCast;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamagePhysical(int damagePhysical) {
        this.damagePhysical = damagePhysical;
    }

    public void setDamageMagic(int damageMagic) {
        this.damageMagic = damageMagic;
    }

    public void setDamagePure(int damagePure) {
        this.damagePure = damagePure;
    }

    public void setDefencePhysical(int defencePhysical) {
        this.defencePhysical = defencePhysical;
    }

    public void setDefenceMagic(int defenceMagic) {
        this.defenceMagic = defenceMagic;
    }

    public void setActivePoint(int activePoint) {
        this.activePoint = activePoint;
    }

    public void setSkillPoint(int skillPoint) {
        this.skillPoint = skillPoint;
    }

    public void setMovePoint(int movePoint) {
        this.movePoint = movePoint;
    }

    public void setSkillDistance(int skillDistance) {
        this.skillDistance = skillDistance;
    }

    public void setMoveCost(int moveCost) {
        this.moveCost = moveCost;
    }

    public void setSkillCost(int skillCost) {
        this.skillCost = skillCost;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void setSkillCast(int skillCast) {
        this.skillCast = skillCast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetailStats that)) return false;
        return health == that.health && damagePhysical == that.damagePhysical && damageMagic == that.damageMagic && damagePure == that.damagePure && defencePhysical == that.defencePhysical && defenceMagic == that.defenceMagic && activePoint == that.activePoint && skillPoint == that.skillPoint && movePoint == that.movePoint && skillDistance == that.skillDistance && moveCost == that.moveCost && skillCost == that.skillCost && moveSpeed == that.moveSpeed && skillCast == that.skillCast;
    }

    @Override
    public int hashCode() {
        return Objects.hash(health, damagePhysical, damageMagic, damagePure, defencePhysical, defenceMagic, activePoint, skillPoint, movePoint, skillDistance, moveCost, skillCost, moveSpeed, skillCast);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
