package ru.safonoviv.roelr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.safonoviv.roelr.Model.Skill.CharacterSkill;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SkillSelector {
    @Autowired
    private Map<String, CharacterSkill> skills;


    public List<CharacterSkill> getSkill(List<String> skillKeys) {
        return skillKeys.stream().map(t -> skills.computeIfAbsent(t, n -> {
            throw new IllegalArgumentException(this.getClass() + " not contain this skill key: " + t);
        })).collect(Collectors.toList());
    }
}
