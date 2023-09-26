package ru.safonoviv.roelr.Model.Skill;

import ru.safonoviv.roelr.Model.Enum.EnumConfig;

import java.util.Map;


public class MomentFireball extends CharacterSkill {

    public MomentFireball(Long id, String name, String description, Map<String, Integer> stats, Map<String, String> behavior, EnumConfig enumConfig) {
        super(id, name, description, stats, behavior, enumConfig);
    }
}
