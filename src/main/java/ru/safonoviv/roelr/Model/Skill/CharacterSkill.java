package ru.safonoviv.roelr.Model.Skill;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import ru.safonoviv.roelr.Model.Character.CharacterPrototype;
import ru.safonoviv.roelr.Model.Enum.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
public abstract class CharacterSkill implements Cloneable, Serializable {
    protected long id;
    protected int multiplier;
    protected int skillCostPoint;
    protected int chargeStartCapacity;
    protected int chargeCurrentCapacity;
    protected int chargeCapacity;
    protected int chargeRound;
    //    protected SkillArea area;
    protected ObjectType objectType;
    protected SkillCollision skillCollision;
    protected SkillMoment skillMoment;
    protected SkillBehaviorAfterCollide skillBehaviorAfterCollide;
    protected String skillName;
    protected String skillDescription;


    @SneakyThrows
    public CharacterSkill(Long id, String name, String description, Map<String, Integer> stats, Map<String, String> skillBehavior, EnumConfig enumConfig) {
        this.id = id;
        this.skillName = name;
        this.skillDescription = description;

        final Map<String, ? extends Enum> attributes = enumConfig.getAttribute(skillBehavior);
        enumConfig.getAttribute(skillBehavior);
        Arrays.stream(CharacterSkill.class.getDeclaredFields()).forEach(t -> {
            final Enum anEnum = attributes.get(t.getGenericType().getTypeName());
            if (anEnum != null) {
                try {
                    t.set(this, anEnum);
                } catch (Exception e) {
                    try {
                        throw e.fillInStackTrace();
                    } catch (Throwable ex) {
                        throw new RuntimeException(ex);
                    }
                }

            } else {
                final Integer fieldValue = stats.get(t.getName());
                if (fieldValue != null) {
                    try {
                        t.set(this, fieldValue);
                    } catch (Exception e) {
                        try {
                            throw e.fillInStackTrace();
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }


        });


    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "CharacterSkill{" +
                "id=" + id +
                ", multiplier=" + multiplier +
                ", skillCostPoint=" + skillCostPoint +
                ", chargeStartCapacity=" + chargeStartCapacity +
                ", chargeCurrentCapacity=" + chargeCurrentCapacity +
                ", chargeCapacity=" + chargeCapacity +
                ", chargeRound=" + chargeRound +
                ", objectType=" + objectType +
                ", skillCollision=" + skillCollision +
                ", skillMoment=" + skillMoment +
                ", skillBehaviorAfterCollide=" + skillBehaviorAfterCollide +
                ", skillName='" + skillName + '\'' +
                ", skillDescription='" + skillDescription + '\'' +
                '}';
    }
}
