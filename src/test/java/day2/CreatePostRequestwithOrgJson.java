package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class CreatePostRequestwithOrgJson {
	@Test(priority = 1)
	public void createData()
	{
		
		JSONObject data = new JSONObject();
		data.put("id","3");
		data.put("name","subhash");
		data.put("age", 20);
		data.put("grade", "B");
		data.put("email", "kalvakolusubhash@gmail.com");
		Map courseName = new HashMap();
		courseName.put("subject", "Maths");
		Object[] courses = {"HTML","JavaScript",courseName};
		data.put("courses", courses);
		System.out.println(data);
		
		given()
		    .contentType("application/json")
		    .body(data.toString())//convert json to string format(no need if use hashmap)
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
