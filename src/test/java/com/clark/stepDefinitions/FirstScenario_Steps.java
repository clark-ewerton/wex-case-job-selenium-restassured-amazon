package com.clark.stepDefinitions;

import com.clark.page.AbstractPageObject;
import com.clark.page.AmazonPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class FirstScenario_Steps extends AbstractPageObject {

	AmazonPage amazon = new AmazonPage();

	@Given("^Search For \"([^\"]*)\" Using The Search Bar$")
	public void search_For_Using_The_Search_Bar(String product) throws Throwable {
		amazon.shouldSearchAProduct(product);
	}

	@Given("^Count The Total List Of Found Products$")
	public void count_The_Total_List_Of_Found_Products() throws Throwable {
		amazon.shouldCountTotalListaProductsFound();
	}

	@Given("^Count The Total Of \"([^\"]*)\" Items Found$")
	public void count_The_Total_Of_Items_Found(String product) throws Throwable {
		amazon.shouldCountTotalIphoneFound(product);
	}

	@Then("^Sure At Least (\\d+)% Of Items Found are \"([^\"]*)\"$")
	public void sure_At_Least_Of_Items_Found_are(int percentage, String product) throws Throwable {
		amazon.shouldValidatePercentageIphoneFound(percentage, product);
	}

}
