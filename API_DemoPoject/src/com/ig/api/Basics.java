package com.ig.api;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.files.Payloads;
import com.files.ReUsableMethods;

public class Basics {
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
		
				//given - all input details 
				//when - Submit the API -resource,http method
				//Then - validate the response
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content Type", "application/json")
		.body(Payloads.reqAddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalsTo("APP")).header("server","");
	                .extract().response().asString();
	
	System.out.println(response);
	
	JsonPath js=new JsonPath(response);
	String placeId=js.getString("place_id");
	System.out.println(placeId);
	
	// Update place
	String newAddress="Summer Walk, Africa";
	
	 given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body("").when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
	.body("msg", equalTo("Address successfully pdated"));
	
	// Get Place
	
	String getPlaceResponse= given().log().all().queryParam("key", "qaclick123")
							.queryParam("place_id", "placeId")
			                .when().get("").then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	//JsonPath js1=new JsonPath(response);
JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);// this will return an object (converting raw string to Json file)
	String actualAddress=js.getString("address"); // from Json extracting string
	System.out.println(actualAddress);
	
	Assert.assertEquals(actualAddress, newAddress);
	}
}
