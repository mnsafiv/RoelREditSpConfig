package ru.safonoviv.roelr.Model.Character;

import ru.safonoviv.roelr.CharacterPrototypeConfig;
import ru.safonoviv.roelr.Model.Enum.*;
import ru.safonoviv.roelr.Model.Skill.CharacterSkill;

import java.util.List;
import java.util.Map;

public class Mage extends CharacterPrototype {


    public Mage(Map<String, Double> stats, List<CharacterSkill> characterSkills, Map<String, String> attribute, EnumConfig enumConfig, CharacterPrototypeConfig characterPrototypeApp) {
        super(stats,characterSkills, attribute, enumConfig, characterPrototypeApp);
    }

    public String getMessage() {
        return toString();
    }


    @Override
    public String getId() {
        return "Mage";
    }


}
