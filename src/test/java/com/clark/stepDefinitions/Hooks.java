package com.clark.stepDefinitions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.clark.driver.DriverManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class Hooks {
	
	@After
	public void takeScreenshot(Scenario scenario) throws IOException {

		FileUtils.deleteDirectory(new File("ScreenShots/"));

		if (scenario.isFailed()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File("./ScreenShots/" + scenario.getName() + ".png"));
				System.out.println("ScreenShot Taken");
			} catch (Exception e) {
				System.out.println("Exception while taking ScreenShot " + e.getMessage());
			}

		}
	}
}
