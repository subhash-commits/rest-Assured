package day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {

	@Test(priority=1)
	public void testCookies() {
		
		given()
		
		.when()
		 .get("https://www.google.com") 
		
		.then()
		//.cookie("AEC","AQTF6Hw7KW7e95gi0-BOK0khyH3D-JLCBliz8KDt5B93RBkfRh2ZaUIO78Q")
		.log().all();
	}

	@Test(priority=2)
	public void getCookiesInfo() {
			
		Response res=given()
			
			.when()
			 .get("https://www.google.com") ;
		
		//get single cookie value
		String cookie = res.getCookie("AEC");
		System.out.println("Value of cookie is ===> " + cookie);
		//get all cookie values
		Map<String, String> cookies = res.getCookies();
		System.out.println("All cookies values ==> \n"+ cookies);
		
	}
}
