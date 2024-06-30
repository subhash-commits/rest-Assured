package day6;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

//Convert Json to JscnSchema(here we will validate our reponse is accorind to difined data types or not)
//https://jsonformatter.org/json-to-jsonschema
public class JsonschemaValidation {
 
	@Test
	public void jsonschemavalidation()
	{

		 given()
		
		.when()
			.get("http://localhost:3000/store")
		
		.then()
		 	.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemavalidation.json"));
	}
}
