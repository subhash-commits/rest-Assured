package day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Logging {
	
	@Test(priority=1)
	public void testLogs()
	{
         given()
		
		.when()
		  .get("https://reqres.in/api/users?page=2&id=7") 
		
		.then()
		 //.log().all();
         //.log().body();
		//.log().cookies();
         .log().headers();
	}
	
}
