package com.globant.morsecode;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import static junit.framework.Assert.assertEquals;

@Slf4j
@SpringBootTest
class MorseCodeApplicationTests {

	@Autowired
	Morse morse;

	@Test
	void should_translate_hello_to_morse_test() {
		String text = "Hello"; //Should be read this from a text file
		String expectedOutput = ".... . .-.. .-.. ---";
		String result = morse.textToMorse(text);

		assertEquals(expectedOutput, result);
	}


	@Test
	void should_translate_the_alphabet() {
		String text = "abcdefghijklmnopqrstuvwxyz"; //Should be read this from a text file
		String expectedOutput = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..";
		String result = morse.textToMorse(text);

		assertEquals(expectedOutput, result);
	}

	@Test
	void should_translate_the_numbers_test() {
		String text = "0123456789"; //Should be read this from a text file
		String expectedOutput = "----- .---- ..--- ...-- ....- ..... -.... --... ---.. ----.";
		String result = morse.textToMorse(text);
		assertEquals(expectedOutput, result);
		log.info(result);
	}

	@Test
	void should_translate_hello_from_morse_plain_text_test() {
		String text = ".... . .-.. .-.. ---";
		String expectedOutput  = "hello"; //Should be read this from a text file
		String result = morse.morseToText(text);

		assertEquals(expectedOutput, result);
	}

	@Test
	void should_translate_the_alphabet_from_morse_test() {
		String text  = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..";
		String expectedOutput = "abcdefghijklmnopqrstuvwxyz"; //Should be read this from a text file
		String result = morse.morseToText(text);

		assertEquals(expectedOutput, result);
	}

	@Test
	void should_translate_the_numbers_from_morse_test() {
		String text = "----- .---- ..--- ...-- ....- ..... -.... --... ---.. ----.";
		String expectedOutput  = "0123456789"; //Should be read this from a text file
		String result = morse.morseToText(text);
		assertEquals(expectedOutput, result);
	}

}


