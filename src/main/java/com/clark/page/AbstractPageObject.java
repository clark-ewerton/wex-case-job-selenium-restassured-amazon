/*
 * MIT License
 *
 * Copyright (c) 2018 Elias Nogueira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.clark.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.clark.driver.DriverManager;

public class AbstractPageObject {

	protected static WebDriverWait wait;

	protected AbstractPageObject() {
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public WebElement procurarElemento(By elemento) {
		return DriverManager.getDriver().findElement(elemento);
	}

	public void preencherCampo(By elemento, CharSequence... texto) {
		procurarElemento(elemento).clear();
		procurarElemento(elemento).sendKeys(texto);
	}

	protected List<WebElement> findElementsBy(By locator) {
		return DriverManager.getDriver().findElements((locator));
	}

	public Boolean esperaElementoParaInteragir(By locator) {
		Boolean elementExists = false;
		wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
		if (wait.until(ExpectedConditions.elementToBeClickable(locator)).isDisplayed()) {
			elementExists = true;
		}

		return elementExists;

	}
}
