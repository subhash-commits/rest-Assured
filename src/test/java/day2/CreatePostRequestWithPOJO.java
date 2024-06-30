package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class CreatePostRequestWithPOJO {
	@Test(priority = 1)
	public void createData()
	{
		
		Pojo_PostRequest data = new Pojo_PostRequest();
		data.setId("3");
		data.setName("subhash");
		data.setAge(20);
		data.setGrade("B");
		data.setEmail("kalvakolusubhash@gmail.com");
		HashMap subject = new HashMap();
		subject.put("subject", "Maths");
		Object[] courses = {"HTML","JavaScript",subject};
		data.setCourses(courses);
		
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
