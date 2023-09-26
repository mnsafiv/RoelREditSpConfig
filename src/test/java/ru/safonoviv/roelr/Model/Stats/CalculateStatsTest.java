package ru.safonoviv.roelr.Model.Stats;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.safonoviv.roelr.CharacterConfig;
import ru.safonoviv.roelr.CharacterPrototypeApp;
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
    private CharacterPrototypeApp factory;
    @Mock
    private CharacterPlayable archerPlayable;
    private final int level=5;



    @BeforeEach
    public void init(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CharacterConfig.class);
        calculateStats=context.getBean("calculateStats",CalculateStats.class);
        factory = context.getBean("characterPrototypeApp",CharacterPrototypeApp.class);
        archerPlayable = factory.getCharacterPrototype("Archer", level);


    }


    @Test
    void calculateStatsByLevel() {
        CharacterPrototype character = Mockito.spy(new Archer());

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

        CharacterPrototypeConfig characterPrototypeConfig = new CharacterPrototypeConfig();
        characterPrototypeConfig.addCharacter("Archer",character);

        MultiplierApp multiplierApp = new MultiplierApp();
        Map<String,Double> bonuses = new HashMap<>();
        multiplierApp.addBonus(bonuses,"job");



        CalculateStats calculateStats1 = Mockito.spy(new CalculateStats(multiplierApp,characterPrototypeConfig));
        CharacterPrototype characterPrototype = characterPrototypeConfig.get("Archer");


        System.out.println();



        DetailStats detailStats = calculateStats.calculateStatsByLevel(character, 3);
        int health = detailStats.getHealth();



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





        CharacterPlayable archer = Mockito.spy(new CharacterPlayable());
        DetailAttribute detailAttribute = Mockito.spy(new DetailAttribute());






        archer.setAttribute(detailAttribute);
        Mockito.doReturn(TierType.boss).when(detailAttribute).getTierType();
        detailAttribute.getTierType();


        assertEquals(level,archerPlayable.getAttribute().getLevel(),0.0);

    }


    @Test
    void getExpByLevel() {
        calculateStats.updateStatsByLevel(archerPlayable,3);
        int expByLevel = calculateStats.getExpByLevel(archerPlayable.getAttribute().getLevel(), archerPlayable.getAttribute().getTierType());
        assertEquals(expByLevel,250,0.0);


    }

    @Test
    void updateStatsByLevel() {
        calculateStats.updateStatsByLevel(archerPlayable,3);
        assertEquals(3,archerPlayable.getAttribute().getLevel(),0.0);




    }

    @Test
    void updateAttributeByExp() {
        calculateStats.updateAttributeByExp(archerPlayable,2000);
        assertEquals(10,archerPlayable.getAttribute().getLevel(),0.0);
    }
}