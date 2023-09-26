package ru.safonoviv.roelr;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.safonoviv.roelr")
@PropertySource("classpath:character.properties")
public class CharacterConfig {



}
