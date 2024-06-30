package day7;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class Authentications {

	@Test(priority=1)
	public void testBasicAuthentication() {
		given()
            .auth().basic("postman", "password")
		.when()
		   .get("https://postman-echo.com/basic-auth")

		.then()
		  .statusCode(200)
		  .body("authenticated", equalTo(true))
		  .log().all();
	}
	
	@Test(priority=2)
	public void testDigestAuthentication() {
		given()
            .auth().digest("postman", "password")
		.when()
		   .get("https://postman-echo.com/basic-auth")

		.then()
		  .statusCode(200)
		  .body("authenticated", equalTo(true))
		  .log().all();
	}
	
	@Test(priority=3)
	public void testpreemptiveAuthentication() {
		given()
            .auth().preemptive().basic("postman", "password")
		 .when()
		   .get("https://postman-echo.com/basic-auth")

		.then()
		  .statusCode(200)
		  .body("authenticated", equalTo(true))
		  .log().all();
	}
	
//	@Test(priority=4)
//	public void testBearerTokenAuthentication() {
//		String BearerToken = "gihub token";
//		given()
//            .headers("Authorization", "Bearer " + BearerToken)
//		 .when()
//		   .get("https://api.github.com/user/repos")
//
//		.then()
//		  .statusCode(200)
//		  .log().all();
//	}
	

	//@Test(priority=5)
	public void testOauth1Authentication() {
		given()
            .auth().oauth("consumerKey", "consumerSecrat", "accessToken", "tokenSecrate")//this is syntax for Oauth 1.0 authorization
		 .when()
		   .get("url")

		.then()
		  .statusCode(200)
		  .log().all();
	}
//	
//	@Test(priority=6)
//	public void testOauth2Authentication() {
//		given()
//            .auth().oauth2("\"gihub token\"")
//		 .when()
//		   .get("https://api.github.com/user/repos")
//
//		.then()
//		  .statusCode(200)
//		  .log().all();
//	}
//	
	@Test(priority=7)
	public void testapiKeyAuthentication() {
		//approach 1
		/*
		 * given() .queryParams("appid", "33e8435eaddb22619a01e014e1421217")//appid is
		 * APIkey .when() .get(
		 * "https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		 * 
		 * .then() .statusCode(200) .log().all();
		 */
		
		given()
		.pathParam("mypath", "data/2.5/forecast/daily")
	       .queryParam("q", "Delhi")
	       .queryParam("units", "metric")
	       .queryParam("cnt", "7")
        .queryParams("appid", "33e8435eaddb22619a01e014e1421217")//appid is APIkey
 
        
		 .when()
		   .get("https://api.openweathermap.org/{mypath}")

		.then()
		  .statusCode(200)
		  .log().all();
	}
	
}
