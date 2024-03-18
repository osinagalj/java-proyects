package com.globant.morsecode;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@SuppressWarnings("NewClassNamingConvention")
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty"},
        glue = {"com.globant.morsecode.steps"})
public class CucumberTestRunnerCase {
}
