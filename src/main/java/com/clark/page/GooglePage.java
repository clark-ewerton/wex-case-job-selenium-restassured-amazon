package com.clark.page;

import static com.clark.elements.GoogleElements.*;

public class GooglePage extends AbstractPageObject {

	public void shouldNavigateToAmazonSearchPage() {
		preencherCampo(SEARCH_BAR, "Amazon Brasil");
		procurarElemento(SEARCH_BUTTON).click();
		procurarElemento(AMAZON_WEBSITE).click();
	}

}
