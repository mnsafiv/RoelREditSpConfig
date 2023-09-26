package ru.safonoviv.roelr.Model.Enum;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@SuppressWarnings("all")
public class EnumConfig {
    private final Map<String, Class<? extends Enum>> maps;


    @Autowired
    public EnumConfig() {
        maps = new HashMap<>();
        maps.put("armorType", ArmorType.class);
        maps.put("attackType", AttackType.class);
        maps.put("tierType", TierType.class);
        maps.put("jobType", JobType.class);
        maps.put("objectType", ObjectType.class);
        maps.put("skillBehaviorAfterCollide", SkillBehaviorAfterCollide.class);
        maps.put("skillCollision", SkillCollision.class);
        maps.put("skillMoment", SkillMoment.class);
    }


    @SneakyThrows
    public Map<String, ? extends Enum> getAttribute(Map<String, String> attributes) {
        Map<String, Enum> values = new HashMap<>();
        attributes.entrySet().forEach(entry -> {
            final Class<? extends Enum> aClass = maps.get(entry.getKey());
            if(aClass==null){
                throw new IllegalArgumentException(this.getClass()+" not contain key: "+entry.getKey());
            }
            final Enum anEnum = Enum.valueOf(aClass, entry.getValue());
            values.put(aClass.getName(), anEnum);
        });
        return values;
    }
}
