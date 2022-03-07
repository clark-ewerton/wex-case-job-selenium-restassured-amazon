package com.clark.page;

import static com.clark.elements.AmazonElements.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	protected List<WebElement> allIphoneProductsPrices;
	protected List<WebElement> allNotIphoneProductsPrices;
	protected List<Double> allPricesInDouble;
	protected List<Double> allIphonePricesInDouble;
	protected List<Double> allNotIphonePricesInDouble;

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

	public double shouldReturnExpensiveProduct(String product) {
		allIphonePricesInDouble = new ArrayList<>();
		allIphoneProductsPrices = findElementsBy(By.xpath("//*[@class='a-section a-spacing-base']//*[contains(text(),'"
				+ product
				+ "') or contains(text(),'iPhone') or contains(text(),'IPHONE') or contains(text(),'IPhone')]/../../../..//*[@class='a-price-whole']"));

		for (WebElement webElement : allIphoneProductsPrices) {
			logger.info("All the iphone prices " + webElement.getText());
			logger.info("All the iphone prices in double " + Double.parseDouble(webElement.getText().replace(".", "")));
			allIphonePricesInDouble.add(Double.parseDouble(webElement.getText().replace(".", "")));
		}

		return Collections.max(allIphonePricesInDouble);

	}

	public double shouldReturnConvertedValueToUSD(String product) {
		double expensiveProductInBRL = shouldReturnExpensiveProduct(product);

		System.err.println("Final price in real " + expensiveProductInBRL);

		return expensiveProductInBRL * convertEndpoint.getUSDCurrency();
	}

	public void shouldCountTotalIphoneFound(String product) {
		esperaElementoParaInteragir(By.xpath("//*[@class='a-section a-spacing-base']//*[contains(text(),'" + product
				+ "') or contains(text(),'iPhone') or contains(text(),'IPHONE') or contains(text(),'IPhone')]"));

		totalIphoneFound = findElementsBy(By.xpath("//*[@class='a-section a-spacing-base']//*[contains(text(),'"
				+ product
				+ "') or contains(text(),'iPhone') or contains(text(),'IPHONE') or contains(text(),'IPhone')]"));

		logger.info("Total iPhone Products Found" + totalIphoneFound.size());

	}

	public void shouldFindProductsThatAreNotIphone() {
		totalNotIphoneFound = findElementsBy(By.xpath(
				"//div[@class='a-section a-spacing-base']//h2//span[not(contains(text(),'Iphone')) and not(contains(text(),'iPhone')) and not(contains(text(),'IPHONE')) and not(contains(text(),'IPhone'))]"));

		logger.info("TOTAL NOT IPHONE PRODUCTS" + totalNotIphoneFound);
	}

	public void shouldValidateFinalValueUSDIsLessThan(double expectedValue, String product) {
		double actualValue = shouldReturnConvertedValueToUSD(product);
		System.err.println("Final price in dollar " + actualValue);

		assertThat(actualValue).isLessThan(expectedValue);
	}

	public void shouldValidateThatAllFoundProductsAreCheaperThanCheapestIphone(String product) {
		List<Double> filteredIphoneValue = new ArrayList<>();
		allIphonePricesInDouble = new ArrayList<>();
		allNotIphonePricesInDouble = new ArrayList<>();
		allIphoneProductsPrices = findElementsBy(By.xpath("//*[@class='a-section a-spacing-base']//*[contains(text(),'"
				+ product
				+ "') or contains(text(),'iPhone') or contains(text(),'IPHONE') or contains(text(),'IPhone')]/../../../..//*[@class='a-price-whole']"));
		allNotIphoneProductsPrices = findElementsBy(NOT_IPHONE_PRODUCT_PRICES);
		double sumOfPriceOfAllNotIphoneProducts;
		double cheapestIphoneValue;

		for (WebElement webElement : allNotIphoneProductsPrices) {
			logger.info("All the prices notIphone " + webElement.getText());
			logger.info(
					"All the prices notIphone in double " + Double.parseDouble(webElement.getText().replace(".", "")));
			allNotIphonePricesInDouble.add(Double.parseDouble(webElement.getText().replace(".", "")));
		}

		sumOfPriceOfAllNotIphoneProducts = allNotIphonePricesInDouble.stream().mapToDouble(f -> f.doubleValue()).sum();

		logger.info("SUM PRICES NOT IPHONE " + sumOfPriceOfAllNotIphoneProducts);

		for (WebElement webElement : allIphoneProductsPrices) {
			logger.info("All the iphone prices " + webElement.getText());
			logger.info("All the iphone prices in double " + Double.parseDouble(webElement.getText().replace(".", "")));
			allIphonePricesInDouble.add(Double.parseDouble(webElement.getText().replace(".", "")));
			filteredIphoneValue = allIphonePricesInDouble
					.stream()
					.filter(num -> num > 1500.0)
					.collect(Collectors.toList());
		}

		cheapestIphoneValue = Collections.min(filteredIphoneValue);

		logger.info("CHEAPEST IPHONE " + cheapestIphoneValue);
		
		assertThat(sumOfPriceOfAllNotIphoneProducts).isLessThan(cheapestIphoneValue);

	}

	public void shouldValidatePercentageIphoneFoundAreGreaterThan(int percentageExpected, String product) {
		switch (product) {
		case "Iphone":
			double actualPercentage = support.calculatePercentage(totalIphoneFound.size(), totalProductsFound.size());

			assertThat(actualPercentage).isGreaterThan(percentageExpected);
			break;

		default:
			logger.error("Product not found!!!");
			break;
		}

	}
}
