package ru.safonoviv.roelr.Model.Stats;

import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.safonoviv.roelr.CharacterPrototypeConfig;
import ru.safonoviv.roelr.Model.Character.Archer;
import ru.safonoviv.roelr.Model.Character.CharacterPrototype;
import ru.safonoviv.roelr.Model.CharacterPlayable;
import ru.safonoviv.roelr.Model.Enum.ArmorType;
import ru.safonoviv.roelr.Model.Enum.AttackType;
import ru.safonoviv.roelr.Model.Enum.JobType;
import ru.safonoviv.roelr.Model.Enum.TierType;
import ru.safonoviv.roelr.MultiplierApp;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculateStatsTest {
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();
    private CalculateStats calculateStats;
    @Mock
    private CharacterPrototype character;
    private CharacterPrototypeConfig characterPrototypeConfig;


    @BeforeEach
    public void init(){
        character = Mockito.spy(new Archer());

        character.setArmorType(ArmorType.light);
        character.setAttackType(AttackType.physical);
        character.setJobType(JobType.range);
        character.setTierType(TierType.elite);


        character.setAtkBase(100);
        character.setDefBase(10);
        character.setHpBase(1000);
        character.setSpdBase(100);
        character.setAtkProgress(10);
        character.setDefProgress(1);
        character.setHpProgress(100);
        character.setSpdProgress(0.02);

        characterPrototypeConfig = new CharacterPrototypeConfig();
        characterPrototypeConfig.addCharacter("Archer",character);

        calculateStats = Mockito.spy(new CalculateStats(getMultiplierApp(),characterPrototypeConfig));


    }

    @NotNull
    private static MultiplierApp getMultiplierApp() {
        MultiplierApp multiplierApp = new MultiplierApp();
        Map<String,Double> jobBonuses = new HashMap<>();
        jobBonuses.put("multiplierPhysicalAttack",1.0);
        jobBonuses.put("multiplierMagicAttack",0.1);
        jobBonuses.put("multiplierPureAttack",0.0);
        jobBonuses.put("moveCost",1.0);
        multiplierApp.addBonus(jobBonuses,JobType.range.name());

        Map<String,Double> tierBonuses = new HashMap<>();
        tierBonuses.put("multiplierProgress",120.0);
        multiplierApp.addBonus(tierBonuses,TierType.elite.name());

        Map<String,Double> attackBonuses = new HashMap<>();
        tierBonuses.put("multiplierMagicAttack",0.5);
        tierBonuses.put("multiplierPhysicalAttack",2.0);
        tierBonuses.put("skillCost",2.0);
        tierBonuses.put("moveCost",0.0);
        multiplierApp.addBonus(attackBonuses,AttackType.physical.name());


        Map<String,Double> armorBonuses = new HashMap<>();
        armorBonuses.put("multiplierPhysicalDefence",0.5);
        armorBonuses.put("multiplierMagicDefence",2.0);
        multiplierApp.addBonus(armorBonuses,ArmorType.light.name());
        return multiplierApp;
    }


    @Test
    void calculateStatsByLevel() {
        //level==3
        int level = 3;
        DetailStats detailStats = calculateStats.calculateStatsByLevel(character, level);

        assertEquals(detailStats.getHealth(),400,0.0);
        assertEquals(detailStats.getDamagePhysical(),390);
        assertEquals(detailStats.getDamageMagic(),78);
        assertEquals(detailStats.getDamagePure(),0);
        assertEquals(detailStats.getDefencePhysical(),6);
        assertEquals(detailStats.getDefenceMagic(),26);

        assertEquals(detailStats.getMoveSpeed(),5);
        assertEquals(detailStats.getMovePoint(),100);
        assertEquals(detailStats.getActivePoint(),50);
        assertEquals(detailStats.getSkillPoint(),100);
        assertEquals(detailStats.getMoveCost(),1);
        assertEquals(detailStats.getSkillDistance(),10);
        assertEquals(detailStats.getSkillCast(),1);
        assertEquals(detailStats.getSkillCost(),100);


        //level==5
        level=5;
        detailStats = calculateStats.calculateStatsByLevel(character, level);

        assertEquals(detailStats.getHealth(),600,0.0);
        assertEquals(detailStats.getDamagePhysical(),450);
        assertEquals(detailStats.getDamageMagic(),90);
        assertEquals(detailStats.getDamagePure(),0);
        assertEquals(detailStats.getDefencePhysical(),7);
        assertEquals(detailStats.getDefenceMagic(),30);

        assertEquals(detailStats.getMoveSpeed(),5);
        assertEquals(detailStats.getMovePoint(),100);
        assertEquals(detailStats.getActivePoint(),50);
        assertEquals(detailStats.getSkillPoint(),100);
        assertEquals(detailStats.getMoveCost(),1);
        assertEquals(detailStats.getSkillDistance(),10);
        assertEquals(detailStats.getSkillCast(),1);
        assertEquals(detailStats.getSkillCost(),100);

    }


    @Test
    void getExpByLevel() {
        int level = 3;
        CharacterPrototype characterPrototype = characterPrototypeConfig.get("Archer");
        CharacterPlayable characterPlayable = new CharacterPlayable(characterPrototype,level,calculateStats);
        int expByLevel = calculateStats.getExpByLevel(characterPlayable.getAttribute().getLevel(), characterPlayable.getAttribute().getTierType());
        assertEquals(expByLevel,250,0.0);

        level=5;
        calculateStats.updateStatsByLevel(characterPlayable,level);
        expByLevel = calculateStats.getExpByLevel(characterPlayable.getAttribute().getLevel(), characterPlayable.getAttribute().getTierType());
        assertEquals(expByLevel,558,0.0);


        level=11;
        calculateStats.updateStatsByLevel(characterPlayable,level);
        expByLevel = calculateStats.getExpByLevel(characterPlayable.getAttribute().getLevel(), characterPlayable.getAttribute().getTierType());
        assertEquals(expByLevel,2062,0.0);


    }

    @Test
    void updateStatsByLevel() {
        int level = 3;
        CharacterPrototype characterPrototype = characterPrototypeConfig.get("Archer");
        CharacterPlayable characterPlayable = new CharacterPlayable(characterPrototype,0,calculateStats);
        calculateStats.updateStatsByLevel(characterPlayable,level);
        assertEquals(characterPlayable.getAttribute().getLevel(),level,0.0);

        level=10;
        calculateStats.updateStatsByLevel(characterPlayable,level);
        assertEquals(characterPlayable.getAttribute().getLevel(),level,0.0);
        
    }

    @Test
    void updateAttributeByExp() {
        CharacterPrototype characterPrototype = characterPrototypeConfig.get("Archer");
        CharacterPlayable characterPlayable = new CharacterPlayable(characterPrototype,0,calculateStats);
        calculateStats.updateAttributeByExp(characterPlayable,2000);
        assertEquals(characterPlayable.getAttribute().getLevel(),10,0.0);

        calculateStats.updateAttributeByExp(characterPlayable,4449);
        assertEquals(characterPlayable.getAttribute().getLevel(),16,0.0);

        calculateStats.updateAttributeByExp(characterPlayable,5558);
        assertEquals(characterPlayable.getAttribute().getLevel(),18,0.0);

    }
}