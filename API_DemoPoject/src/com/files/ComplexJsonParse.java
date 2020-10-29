package com.files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js=new JsonPath(Payloads.CoursePrice());
		
		int count=js.getInt("course.size()"); // course is an array so we can use size method.
		System.out.println(count);
		
		// Print purchase amount
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//Print title of the first course
		String titleFirstCourse=js.get("courses[0].title");
		System.out.println(titleFirstCourse);
		
		//Print all course title and their respective prices. No.of elements present in the array
		for(int i=0;i<count;i++) { // dynamic json if element increases in future then it wont be problem
			String courseTitles=js.get("courses["+i+"].title");
			// title dont want to store in variable ..can write it with SOP with toString
			System.out.println(js.get("courses["+i+"].title").toString());
			System.out.println(courseTitles);
			//S.O.P always expects string arg
		}
		for(int i=0;i<count;i++) {
			String courseTitles=js.getString("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA"))
			{
				int copies=js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
	}
}
