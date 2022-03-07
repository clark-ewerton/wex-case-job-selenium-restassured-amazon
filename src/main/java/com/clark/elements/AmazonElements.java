package com.clark.elements;

import org.openqa.selenium.By;

public class AmazonElements {

	public static final By SEARCH_BAR = By.id("twotabsearchtextbox");
	public static final By SEARCH_BUTTON = By.id("nav-search-submit-button");
	public static final By PRODUCT = By.xpath("//*[@class='s-image']");
	public static final By PRODUCT_PRICES = By.xpath("//*[@class='a-price-whole']");
	public static final By NOT_IPHONE_PRODUCT_PRICES = By.xpath(
			"//div[@class='a-section a-spacing-base']//h2//span[not(contains(text(),'Iphone')) and not(contains(text(),'iPhone')) and not(contains(text(),'IPHONE')) and not(contains(text(),'IPhone'))]/../../../..//*[@class='a-price-whole']");

}
