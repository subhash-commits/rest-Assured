package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CreatePostRequestWithJacksonDataBind {
	@Test(priority = 1)
	public void createData() {
		// Create ObjectMapper
		ObjectMapper mapper = new ObjectMapper();

		// Create main JSON object
		ObjectNode data = mapper.createObjectNode();

		data.put("id", "3");
		data.put("name", "subhash");
		data.put("age", 20);
		data.put("grade", "B");
		data.put("email", "kalvakolusubhash@gmail.com");
		  // Create nested course object
        ObjectNode courseObject = mapper.createObjectNode();
        courseObject.put("subject", "Maths");

        // Create an array and add both strings and the course object
        ArrayNode courses = mapper.createArrayNode();
        courses.add("HTML");
        courses.add("JavaScript");
        courses.add(courseObject);

        // Add the courses array to the main JSON object
        data.set("courses", courses);
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
	public void deleteData() throws InterruptedException {
		Thread.sleep(10000);
		given().when().delete("http://localhost:3000/students/3").then().statusCode(200);
	}
}
