package com.clark.endpoints;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.clark.config.Configuration;
import com.clark.config.ConfigurationManager;
import com.clark.specs.InitialStateSpecs;

import io.restassured.response.Response;

public class Convert {
	
	Configuration configuration = ConfigurationManager.configuration();
	private static final Logger logger = LogManager.getLogger(Convert.class);

	public double getUSDCurrency() {
		Response response = 
	         given().
	        	spec(InitialStateSpecs.set()).
	         when().
	         	queryParam("q", "BRL_USD"). 
	         	queryParam("compact", "ultra"). 
	         	queryParam("apiKey", configuration.baseToken()). 
	            get("/convert").
	            	then().
	            	log().all().
	            	statusCode(HttpStatus.SC_OK).
	            	extract().response();
		
		logger.info(response);
		 
		return response.jsonPath().getDouble("BRL_USD");

	}

}
