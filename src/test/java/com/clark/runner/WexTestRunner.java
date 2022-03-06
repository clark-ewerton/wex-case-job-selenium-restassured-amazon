package com.clark.runner;
import static com.clark.config.ConfigurationManager.configuration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.clark.driver.DriverManager;
import com.clark.driver.TargetFactory;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.clark.stepDefinitions"},
        tags = {"~@ignore"},
        		plugin = { "pretty", "json:json/cucumber.json"}
        )
public class WexTestRunner {
	
	@BeforeClass
    public static void beforeSuite() {
        WebDriver driver = new TargetFactory().createInstance("chrome");
        
        DriverManager.setDriver(driver);

        DriverManager.getDriver().get(configuration().url());
        
    }
	
    @AfterClass
    public static void postCondition() {
        DriverManager.quit();
    }

}
