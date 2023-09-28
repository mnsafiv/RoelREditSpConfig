package ru.safonoviv.roelr.Model.Stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.safonoviv.roelr.Model.Enum.ArmorType;
import ru.safonoviv.roelr.Model.Enum.AttackType;
import ru.safonoviv.roelr.Model.Enum.JobType;
import ru.safonoviv.roelr.Model.Enum.TierType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailAttribute {
    private int level;
    private int curExp;
    private int availableExp;
    private JobType jobType;
    private AttackType attackType;
    private ArmorType armorType;
    private TierType tierType;

}
