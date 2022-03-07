package com.clark.stepDefinitions;

import com.clark.page.AmazonPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ThirdScenario_Steps {

	AmazonPage amazon = new AmazonPage();

	@When("^Find Products Which Are Not \"([^\"]*)\"$")
	public void find_Products_Which_Are_Not(String arg1) throws Throwable {
		amazon.shouldFindProductsThatAreNotIphone();
	}

	@Then("^Make Sure All Found Products Are Cheaper Than The Cheapest \"([^\"]*)\"$")
	public void make_Sure_All_Found_Products_Are_Cheaper_Than_The_Cheapest(String product) throws Throwable {
		amazon.shouldValidateThatAllFoundProductsAreCheaperThanCheapestIphone(product);
	}

}
