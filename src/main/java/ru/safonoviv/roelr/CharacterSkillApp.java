package ru.safonoviv.roelr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.safonoviv.roelr.Model.Enum.EnumConfig;
import ru.safonoviv.roelr.Model.Skill.Arrow;
import ru.safonoviv.roelr.Model.Skill.CharacterSkill;
import ru.safonoviv.roelr.Model.Skill.Fireball;
import ru.safonoviv.roelr.Model.Skill.MomentFireball;

import java.util.List;
import java.util.Map;

@Component
public class CharacterSkillApp {
    @Autowired
    private EnumConfig enumConfig;


    @Bean
    public Fireball fireball(@Value("#{${skill.fireball.id}}") Long id,
                             @Value("#{${skill.fireball.name}}") String name,
                             @Value("#{${skill.fireball.description}}") String description,
                             @Value("#{${skill.fireball.stats}}") Map<String, Integer> stats,
                             @Value("#{${skill.fireball.behavior}}") Map<String, String> behavior){
        return new Fireball(id,name,description,stats,behavior,enumConfig);
    }

    @Bean
    public Arrow arrow(@Value("#{${skill.arrow.id}}") Long id,
                       @Value("#{${skill.arrow.name}}") String name,
                       @Value("#{${skill.arrow.description}}") String description,
                       @Value("#{${skill.arrow.stats}}") Map<String, Integer> stats,
                       @Value("#{${skill.arrow.behavior}}") Map<String, String> behavior){
        return new Arrow(id,name,description,stats,behavior,enumConfig);
    }

    @Bean
    public MomentFireball momentFireball(@Value("#{${skill.momentFireball.id}}") Long id,
                                         @Value("#{${skill.momentFireball.name}}") String name,
                                         @Value("#{${skill.momentFireball.description}}") String description,
                                         @Value("#{${skill.momentFireball.stats}}") Map<String, Integer> stats,
                                         @Value("#{${skill.momentFireball.behavior}}") Map<String, String> behavior){
        return new MomentFireball(id,name,description,stats,behavior,enumConfig);
    }


    public List<CharacterSkill> getSkill(List<String> skillKeys) {
        return null;
    }


}
