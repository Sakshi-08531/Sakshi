package com.ig.api;


import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Assertion3 {
	public static void main(String[] args) {

		// Get Place
	Response response= given().log().all()
			                .when().get("http://demo4032024.mockable.io/apitest").then().assertThat().log().all().statusCode(200).extract().response();
		
	String contentType = response.header("Content-Type");
	 System.out.println("Content-Type value: " + contentType);
	
	String serverType =  response.header("Server");
	 System.out.println("Server value: " + serverType);
	 
	 String acceptLanguage = response.header("Content-Encoding");
	 System.out.println("Content-Encoding: " + acceptLanguage);
	 
		}
	}

