package day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class CreatePostRequestWithHashMap {

	@Test(priority = 1)
	public void createData()
	{
		
		LinkedHashMap data = new LinkedHashMap();
		data.put("id","3");
		data.put("name","subhash");
		data.put("age", 20);
		data.put("grade", "B");
		data.put("email", "kalvakolusubhash@gmail.com");
		//String[] courses = {"HTML","JavaScript"};
		Map courseName = new HashMap();
		courseName.put("subject", "Maths");
		Object[] courses = {"HTML","JavaScript",courseName};
		data.put("courses", courses);
		System.out.println(data);
		
		given()
		    .contentType("application/json")
		    .body(data)
		.when()
		    .post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("subhash"))
			.body("age", equalTo(20))
			.body("grade", equalTo("B"))
			.body("email", equalTo("kalvakolusubhash@gmail.com"))
			.body("courses[0]", equalTo("HTML"))
			.body("courses[1]", equalTo("JavaScript"))
			.body("courses[2].subject", equalTo("Maths"))
			.header("Content-Type", "application/json")
			.log()	
			.all();
			
	}
	
	@Test(priority = 2)
	public void deleteData() throws InterruptedException
	{
		Thread.sleep(10000);
		 given()
		.when()
		    .delete("http://localhost:3000/students/3")
		.then()
			.statusCode(200);    
	}

}
