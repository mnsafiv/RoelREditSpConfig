package ru.safonoviv.roelr.Model.Stats;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.safonoviv.roelr.CharacterPrototypeConfig;
import ru.safonoviv.roelr.Model.Character.CharacterPrototype;
import ru.safonoviv.roelr.Model.CharacterPlayable;
import ru.safonoviv.roelr.Model.Enum.TierType;
import ru.safonoviv.roelr.MultiplierApp;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class CalculateStats {

    @Autowired
    private MultiplierApp multiplier;
    @Autowired
    private CharacterPrototypeConfig characterPrototype;

    public CalculateStats(MultiplierApp multiplier, CharacterPrototypeConfig characterPrototype) {
        this.multiplier = multiplier;
        this.characterPrototype = characterPrototype;
    }

    public DetailStats calculateStatsByLevel(CharacterPrototype stats, int level) {

        MultiplierBonus bonusArmor = multiplier.getBonus(stats.getArmorType().name());
        MultiplierBonus bonusJob = multiplier.getBonus(stats.getJobType().name());
        MultiplierBonus bonusAttack = multiplier.getBonus(stats.getAttackType().name());
        MultiplierBonus bonusTier = multiplier.getBonus(stats.getTierType().name());


        Map<String, Double> currentBonus = Arrays.stream(Bonus.values())
                .collect(Collectors.toMap(String::valueOf, t -> 0.0, (a, b) -> b));

        Stream.of(bonusAttack, bonusArmor, bonusJob, bonusTier).forEach(t -> {
            t.getBonuses().forEach((key, value) -> currentBonus.put(key, currentBonus.get(key) + value));
        });

        DetailStats detailStats = new DetailStats();

        int hp = (int) (stats.getAtkBase() + stats.getHpProgress() * level);
        int atk = (int) (stats.getAtkBase() + stats.getAtkProgress() * level);
        int def = (int) (stats.getDefBase() + stats.getDefProgress() * level);
        int spd = (int) (stats.getSpdBase() + stats.getSpdProgress() * level);


        detailStats.setDamagePhysical((int) (atk * currentBonus.get(Bonus.multiplierPhysicalAttack.name())));
        detailStats.setDamageMagic((int) (atk * currentBonus.get(Bonus.multiplierMagicAttack.name())));
        detailStats.setDamagePure((int) (atk * currentBonus.get(Bonus.multiplierPureAttack.name())));

        detailStats.setDefencePhysical((int) (def * currentBonus.get(Bonus.multiplierPhysicalDefence.name())));
        detailStats.setDefenceMagic((int) (def * currentBonus.get(Bonus.multiplierMagicDefence.name())));

        detailStats.setMoveCost((int) (currentBonus.get(Bonus.moveCost.name())*1));
        detailStats.setSkillCost((int) (currentBonus.get(Bonus.skillCost.name()) * 50));


        detailStats.setSkillDistance(10);
        detailStats.setHealth(hp);


        //need logic
        detailStats.setActivePoint(50);
        detailStats.setSkillPoint(100);
        detailStats.setMovePoint(100);

        detailStats.setMoveSpeed(spd / 20);
        detailStats.setSkillCast(1);


        return detailStats;
    }


    public int getProgressExp(int level, double multiplierProgress) {
        int toPrevLevel = (int) multiplierProgress;
        int exp = 0;
        for (int next = 0; level-- > 1; next++) {
            toPrevLevel = (int) (Math.sqrt(next * multiplierProgress) + toPrevLevel);
            exp += toPrevLevel;
        }
        return exp;
    }

    public int getProgressLevel(int exp, double multiplierProgress) {
        int level = 0;
        int toPrevLevel = (int) multiplierProgress;
        for (int next = 0; exp > 0; next++) {
            toPrevLevel = (int) (Math.sqrt(next * multiplierProgress) + toPrevLevel);
            exp -= toPrevLevel;
            level++;
        }
        return level;
    }

    public int getExpByLevel(int level, @NotNull TierType tierType) {
        Double multiplierProgress = multiplier.getBonus(tierType.name()).getBonuses().get(Bonus.multiplierProgress.name());
        return getProgressExp(level, multiplierProgress);
    }



    public void updateStatsByLevel(@NotNull CharacterPlayable characterPlayable, int level) {
        characterPlayable.getAttribute().setLevel(level);
        CharacterPrototype characterPrototype = this.characterPrototype.get(characterPlayable.getKeyId());
        DetailStats detailStats = calculateStatsByLevel(characterPrototype, level);
        characterPlayable.setStats(detailStats);
    }

    public void updateAttributeByExp(@NotNull CharacterPlayable characterPlayable, int curExp) {
        Double multiplierProgress = multiplier.getBonus(characterPlayable.getAttribute().getTierType().name()).getBonuses().get(Bonus.multiplierProgress.name());
        int level = getProgressLevel(curExp, multiplierProgress);
        updateStatsByLevel(characterPlayable,level);
    }

    public MultiplierApp getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(MultiplierApp multiplier) {
        this.multiplier = multiplier;
    }

    public CharacterPrototypeConfig getCharacterPrototype() {
        return characterPrototype;
    }

    public void setCharacterPrototype(CharacterPrototypeConfig characterPrototype) {
        this.characterPrototype = characterPrototype;
    }
}
