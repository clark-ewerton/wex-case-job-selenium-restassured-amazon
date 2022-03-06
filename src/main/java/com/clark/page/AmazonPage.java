package com.clark.page;

import static com.clark.elements.AmazonElements.PRODUCT;
import static com.clark.elements.AmazonElements.PRODUCT_PRICE;
import static com.clark.elements.AmazonElements.SEARCH_BAR;
import static com.clark.elements.AmazonElements.SEARCH_BUTTON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.clark.config.Configuration;
import com.clark.endpoints.Convert;
import com.clark.support.Utils;

public class AmazonPage extends AbstractPageObject {

	protected static Configuration configuration;

	protected List<WebElement> totalProductsFound;
	protected List<WebElement> totalIphoneFound;
	protected List<WebElement> totalNotIphoneFound;
	protected List<WebElement> allProductsPrices;
	Utils support = new Utils();
	Convert convertEndpoint = new Convert();
	private static final Logger logger = LogManager.getLogger(AmazonPage.class);

	public void shouldSearchAProduct(String product) {
		preencherCampo(SEARCH_BAR, product);
		procurarElemento(SEARCH_BUTTON).click();
	}

	public void shouldCountTotalListaProductsFound() {
		esperaElementoParaInteragir(PRODUCT);
		totalProductsFound = findElementsBy(PRODUCT);

		logger.info("Total Products Found" + totalProductsFound.size());

	}

	public static void main(String[] args) {
		List<Float> items = Arrays.asList(94f, 4500f, 16.0f, -6.0f, 5.0f);
		double max = items.stream().max(Comparator.naturalOrder()).get();
		double min = items.stream().min(Comparator.naturalOrder()).get();
		System.out.println(min + " " + max);
	}

	public double shouldReturnExpensiveProduct() {
		List<Double> allPricesInDouble = new ArrayList<>();
		allProductsPrices = findElementsBy(PRODUCT_PRICE);

		for (WebElement webElement : allProductsPrices) {
			logger.info("All the prices " + webElement.getText());
			logger.info("All the prices in double " + Double.parseDouble(webElement.getText().replace(".", "")));
			allPricesInDouble.add(Double.parseDouble(webElement.getText().replace(".", "")));
		}

		return Collections.max(allPricesInDouble);

	}

	public double shouldReturnConvertedValueToUSD() {
		double expensiveProductInBRL = shouldReturnExpensiveProduct();

		System.err.println("Final price in real " + expensiveProductInBRL);

		return expensiveProductInBRL * convertEndpoint.getUSDCurrency();
	}

	public void shouldValidateFinalValueUSDIsBiggerThan(double expectedValue) {
		double actualValue = shouldReturnConvertedValueToUSD();
		System.err.println("Final price in dollar " + actualValue);
		if (actualValue < expectedValue) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("The current value in USD is bigger than 2000 USD");
		}
	}

	public void shouldCountTotalIphoneFound(String product) {
		esperaElementoParaInteragir(By.xpath("//*[@class='a-section a-spacing-base']//*[contains(text(),'" + product
				+ "') or contains(text(),'iPhone') or contains(text(),'IPHONE') or contains(text(),'IPhone')]"));

		totalIphoneFound = findElementsBy(By.xpath("//*[@class='a-section a-spacing-base']//*[contains(text(),'"
				+ product + "') or contains(text(),'iPhone') or contains(text(),'IPHONE')]"));

		logger.info("Total iPhone Products Found" + totalIphoneFound.size());

	}

	public void shouldFindProductsThatAreNotIphone() {
		totalNotIphoneFound = findElementsBy(By.xpath(
				"//div[@class='a-section a-spacing-base']//h2//span[not(contains(text(),'Iphone')) and not(contains(text(),'iPhone')) and not(contains(text(),'IPHONE')) and not(contains(text(),'IPhone'))]"));
		logger.info("TOTAL NOT IPHONE PRODUCTS" + totalNotIphoneFound);
	}

	public void shouldValidateThatAllFoundProductsAreCheaperThanCheapestIphone() {

	}

	public void shouldValidatePercentageIphoneFound(int percentageExpected, String product) {
		switch (product) {
		case "Iphone":
			double actualPercentage = support.calculatePercentage(totalIphoneFound.size(), totalProductsFound.size());

			if (actualPercentage > percentageExpected) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("The number of iphones found is less than 80%");
			}
			break;

		default:
			Assert.fail("Didn't find the right product");
			break;
		}

	}
}
