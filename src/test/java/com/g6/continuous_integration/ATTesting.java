package com.g6.continuous_integration;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "Features"
	,glue={"stepDefinition"}
)


public class ATTesting {

}
