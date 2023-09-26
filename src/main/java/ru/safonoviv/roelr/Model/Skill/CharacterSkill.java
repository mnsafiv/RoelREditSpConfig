package ru.safonoviv.roelr.Model.Skill;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import ru.safonoviv.roelr.Model.Character.CharacterPrototype;
import ru.safonoviv.roelr.Model.Enum.*;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
public abstract class CharacterSkill implements Cloneable, Serializable {
    protected long id;

    protected int skill_id;
    protected int multiplier;
    protected int skillCostPoint;
    protected int chargeStartCapacity;
    protected int chargeCurrentCapacity;
    protected int chargeCapacity;
    protected int chargeRound;

    protected int area_id;
//    protected SkillArea area;
    protected ObjectType objectType;
    protected SkillCollision skillCollision;
    protected SkillMoment skillMoment;
    protected SkillBehaviorAfterCollide skillBehaviorAfterCollide;
    protected String skillName;
    protected String skillDescription;


    @SneakyThrows
    public CharacterSkill(Long id, String name, String description, Map<String, Integer> stats, Map<String, String> skillBehavior, EnumConfig enumConfig) {
        final Map<String, ? extends Enum> attributes = enumConfig.getAttribute(skillBehavior);
        Arrays.stream(CharacterPrototype.class.getDeclaredFields()).forEach(t -> {
            final Enum anEnum = attributes.get(t.getGenericType().getTypeName());
            if (anEnum != null) {
                try {
                    t.set(this, anEnum);
                } catch (Exception e) {
                    System.out.println(e.fillInStackTrace());
                }

            }

        });


        this.id = id;
        this.skillName = name;
        this.skillDescription = description;
        skillCollision = SkillCollision.valueOf(skillBehavior.get("skillCollision"));
        skillBehaviorAfterCollide = SkillBehaviorAfterCollide.valueOf(skillBehavior.get("skillBehaviorAfterCollide"));
        skillMoment = SkillMoment.valueOf(skillBehavior.get("skillMoment"));
        objectType = ObjectType.valueOf(skillBehavior.get("objectType"));
        skillCostPoint = stats.get("skillCostPoint");
        multiplier = stats.get("multiplier");
        chargeRound = stats.get("chargeRound");
        chargeCapacity = stats.get("chargeCapacity");
        chargeCurrentCapacity = stats.get("chargeStartCapacity");
        chargeStartCapacity = stats.get("chargeStartCapacity");
    }



    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "CharacterSkill{" +
                "id=" + id +
                ", skill_id=" + skill_id +
                ", multiplier=" + multiplier +
                ", skillCostPoint=" + skillCostPoint +
                ", chargeStartCapacity=" + chargeStartCapacity +
                ", chargeCurrentCapacity=" + chargeCurrentCapacity +
                ", chargeCapacity=" + chargeCapacity +
                ", chargeRound=" + chargeRound +
                ", area_id=" + area_id +
                ", objectType=" + objectType +
                ", skillCollision=" + skillCollision +
                ", skillMoment=" + skillMoment +
                ", skillBehaviorAfterCollide=" + skillBehaviorAfterCollide +
                ", skillName='" + skillName + '\'' +
                ", skillDescription='" + skillDescription + '\'' +
                '}';
    }
}
