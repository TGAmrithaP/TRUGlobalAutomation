package MyRunner;

import org.junit.runner.RunWith;
import org.testng.annotations.Test;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@Test
@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\TG1698\\eclipse-workspace\\TRUGlobalAutomation\\src\\test\\resources\\Features",
dryRun= false,   //Only if we want to get Only Snippet without running whole program than put as true 
glue = {"StepDefinitions"}, // its represent the package name in step definitions
monochrome = true,  //Display the console output in proper readable format
//plugin = { "pretty",  "com.epam.reportportal.cucumber.ScenarioReporter" }
plugin = { "pretty",  "com.epam.reportportal.cucumber.StepReporter" }

//tags = "@tag2"
// tags = {"@SmokeTest" , "@End2End"} => AND
// tags = {"@SmokeTest, @End2End"} => OR




		)


public class Runner extends AbstractTestNGCucumberTests{

}
