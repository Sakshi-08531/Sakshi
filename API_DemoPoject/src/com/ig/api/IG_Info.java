package com.ig.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import org.testng.Assert;
import com.files.ReUsableMethods;

public class IG_Info {

		public static void main(String[] args) {
		String company="ABC Infotech";
	// Get 
		
		  String response= given().log().all()
		  .when().get("http://demo4032024.mockable.io/apitest").then().assertThat().log
		  ().all().statusCode(200).extract().response().asString();
		 
		/*
		 * Response response= given().log().all()
		 * .when().get("http://demo4032024.mockable.io/apitest").then().assertThat().log
		 * ().all().statusCode(200).extract().response();
		 */
		
	//JsonPath js1=new JsonPath(response);
JsonPath js1 = ReUsableMethods.rawToJson(response);// this will return an object (converting raw string to Json file)
String status=js1.getString("status");	
String age=js1.getString("employeeData.age"); 
String role=js1.getString("employeeData.role");
String dob=js1.getString("employeeData.dob");
String message=js1.getString("message");// from Json extracting string
String actualCompany=js1.getString("employeeData.dob");
System.out.println("Status = "+status);	
System.out.println("Age = "+age);
System.out.println("Role = "+role);
System.out.println("Dob = "+dob);
System.out.println("Message = "+message);

Assert.assertEquals(actualCompany, company);
		}
		
		
public void validation(String company) {
	JsonPath js1 = ReUsableMethods.rawToJson(response);
	String validation=given().then().extract().body().jsonPath().getString("employeeData.company");
	Assert.assertEquals("IG Infotech +", company);
	}
}
