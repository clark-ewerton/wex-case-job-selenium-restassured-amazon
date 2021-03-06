package com.clark.stepDefinitions;

import com.clark.page.AmazonPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SecondScenario_Steps {

	AmazonPage amazon = new AmazonPage();

	@Given("^Find The More Expensive \"([^\"]*)\" In Page$")
	public void find_The_More_Expensive_In_Page(String product) throws Throwable {
		amazon.shouldReturnExpensiveProduct(product);
	}

	@When("^Convert Its Value To USD$")
	public void convert_Its_Value_To_USD() throws Throwable {
		String product = "Iphone";
		amazon.shouldReturnConvertedValueToUSD(product);
	}

	@Then("^Make Sure The Converted Value Is Not Greater Than (\\d+) USD$")
	public void make_Sure_The_Converted_Value_Is_Not_Greater_Than_USD(int valueExpected) throws Throwable {
		String product = "Iphone";
		amazon.shouldValidateFinalValueUSDIsLessThan(Double.valueOf(valueExpected), product);
	}
}
