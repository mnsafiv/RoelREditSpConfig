package ru.safonoviv.roelr;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.safonoviv.roelr.Model.CharacterPlayable;
import ru.safonoviv.roelr.Model.Stats.CalculateStats;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CharacterConfig.class);

        CalculateStats calculateStats=context.getBean("calculateStats",CalculateStats.class);
        CharacterPrototypeApp factory = context.getBean("characterPrototypeApp",CharacterPrototypeApp.class);

        CharacterPlayable archerPlayable = factory.getCharacterPrototype("Archer", 5);
        calculateStats.updateStatsByLevel(archerPlayable,4);


    }
}