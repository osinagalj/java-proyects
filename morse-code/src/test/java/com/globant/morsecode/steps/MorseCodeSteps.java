package com.globant.morsecode.steps;

import com.globant.morsecode.Morse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class MorseCodeSteps {

    private StringBuilder result = new StringBuilder();

    private List<String> currentList;

    @Autowired
    Morse morse;

    @Given("A new string")
    public void reset() {
        result = new StringBuilder();
    }

    @When("Translate {string} from plain text")
    public void translateStringToMorse(String text) {
        System.out.println("text = " + text);
        result.append(morse.textToMorse(text));
    }

    @Then("The result in morse code should be {string}")
    public void thenMorseCodeResultIs(String expected) {
        Assertions.assertEquals(expected, result.toString());
    }


    @When("Translate {string} from morse code")
    public void translateStringToPlainText(String text) {
        System.out.println("text = " + text);
        result.append(morse.morseToText(text));
    }

    @Then("The result in plain text should be {string}")
    public void thenPlainTextResultIs(String expected) {
        Assertions.assertEquals(expected, result.toString());
    }

    @When("Translate the following list$")
    public void translateListToMorseCode(List<String> input){
        currentList = input.stream()
                .map(s -> morse.textToMorse(s))
                .toList();
        System.out.println();
    }

    @Then("The result should be$")
    public void thenMorseCodeList(List<String> expectedOutput){
        Assertions.assertEquals(currentList, expectedOutput);
    }
}
