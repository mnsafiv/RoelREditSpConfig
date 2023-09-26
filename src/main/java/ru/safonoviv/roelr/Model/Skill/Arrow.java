package ru.safonoviv.roelr.Model.Skill;

import ru.safonoviv.roelr.Model.Enum.EnumConfig;

import java.util.Map;


public class Arrow extends CharacterSkill {

    public Arrow(Long id, String name, String description, Map<String, Integer> stats, Map<String, String> behavior, EnumConfig enumConfig) {
        super(id, name, description, stats, behavior, enumConfig);
    }
}
