package ru.safonoviv.roelr;

import org.springframework.stereotype.Component;
import ru.safonoviv.roelr.Model.Character.CharacterPrototype;

import java.util.HashMap;
import java.util.Map;

@Component
public class CharacterPrototypeConfig {
    private final Map<String, CharacterPrototype> characterPrototypes=new HashMap<>();
    public void addCharacter(String key,CharacterPrototype characterPrototype){
        CharacterPrototype put = characterPrototypes.put(key, characterPrototype);
        if(put!=null){
            throw new IllegalArgumentException(key+" already contains");
        }
    }

    public CharacterPrototype get(String name) {
        return characterPrototypes.get(name);
    }
}
