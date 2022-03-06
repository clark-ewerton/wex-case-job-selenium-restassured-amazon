package com.clark.stepDefinitions;

import com.clark.page.GooglePage;

import cucumber.api.java.en.Given;

public class SetupScenario_Steps {

	GooglePage google = new GooglePage();

	@Given("^I set up the navigation to Amazon WebSite$")
	public void i_set_up_the_navigation_to_Amazon_WebSite() throws Throwable {
		google.shouldNavigateToAmazonSearchPage();
	}

}
