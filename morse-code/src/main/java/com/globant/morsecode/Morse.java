package com.globant.morsecode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.globant.morsecode.GlobalApplicationContext.getBean;

@Service
public class Morse {

    /**
     * Translate plain text to morse code using universal morse code.
     * @param plainText the text to be translated to morse code.
     * @return the morse code corresponding to the input plain text.
     */
    public String textToMorse(String plainText){
        Map<String,String> dictionary = ((MorseConfig) getBean(MorseConfig.class)).getValues();

        return plainText.toLowerCase().chars()
                .mapToObj(ch -> dictionary.get(String.valueOf((char) ch)))
                .collect(Collectors.joining(" "));
    }


    /**
     * Translate morse code to plain text code using universal morse code.
     * @param morseCode the morse code to be translated to plain text.
     * @return text corresponding to the input morse.
     */
    public String morseToText(String morseCode){
        Map<String,String> dictionary = ((MorseConfig) getBean(MorseConfig.class)).getValues();
        Map<String, String> invertedMap = getKeysByValue(dictionary);

        return Arrays.stream(morseCode.split(" "))
                .map(word -> word.split(" "))
                .flatMap(Arrays::stream)
                .map(invertedMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.joining());
    }

    public static <T, E> Map<T, E> getKeysByValue(Map<E, T> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
}
