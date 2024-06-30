package day5;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;
public class ParsingXMLResponse {

	@Test
	public void testXMLResponse() {
		
		 given()
		
		.when()
			.get("https://earthquake.usgs.gov/fdsnws/event/1/query?format=xml&starttime=2023-01-01&endtime=2023-01-02")
		
		.then()
		 	.statusCode(200)
		 	.header("Content-Type", "application/xml; charset=utf-8");
	}
}
