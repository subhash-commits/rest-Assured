package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


public class CreatePostRequestwithExternalJsonFile {
	@Test(priority = 1)
	public void createData() throws FileNotFoundException
	{
		
		File file = new File(".\\body.json");
		FileReader fr = new FileReader(file);
		
		JSONTokener jsontoken = new JSONTokener(fr);
		
		JSONObject jsonobj = new JSONObject(jsontoken);
		
		given()
		    .contentType("application/json")
		    .body(jsonobj.toString())
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
