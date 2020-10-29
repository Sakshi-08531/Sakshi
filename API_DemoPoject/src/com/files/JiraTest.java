package com.files;
import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class JiraTest {

	public static void main(String[] args) {
		RestAssured.baseURI="http://localhost:8080";
		
		//Login Scenario
		SessionFilter session=new SessionFilter();// session object remembers the response of this session. we use session obj in all subsequent code.This session is ready to listen if thr is new session is created
		String response=given().header("Content-Type","application/json")
				.body("").log().all().filter(session).when()
				.post("/rest/auth/1/session").then().log().all().extract().response().asString();
		
		
		given().pathParam("key","10101" ).log().all().header("Content-Type","application/json").body("").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201);
	
	//Add attachment
		given().header("","").filter(session).pathParam("", "").header("", "").multiPart("file",new File("jira.txt")).when().post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
		
		// integrating query and path param (Get issue)
		String issueDetails=given().filter(session).pathParam("key", "10101")
		.queryParam("fields", "comment").log().all().when().get("/rest/api/2/issue/key}")
		.then().log().all().extract().response().asString();
		
	//given().header().filter(session).path
	
}
}