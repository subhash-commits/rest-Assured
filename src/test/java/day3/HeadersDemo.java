package day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Headers;
import io.restassured.response.Response;
public class HeadersDemo {

	@Test(priority=1)
	public void testHeaders()
	{
         given()
		
		.when()
		  .get("https://www.google.com") 
		
		.then()
		  .header("Content-Type", "text/html; charset=ISO-8859-1")
		  .and()
          .header("Content-Encoding", "gzip")
          .and()
          .header("Server", "gws")
          .and()
          .cookie("AEC","AQTF6Hw7KW7e95gi0-BOK0khyH3D-JLCBliz8KDt5B93RBkfRh2ZaUIO78Q");//commenting this validation because everytime cookie value will not same to make it not testcases fail
		 //.log().all();
	}
	
	@Test(priority=2)
	public void getHeaders()
	{
        Response res = given()
		
		.when()
		  .get("https://www.google.com");
        
        //get single header value
		String singleheadervalue = res.getHeader("Content-Type");
		System.out.println("Header Value is ==> "+singleheadervalue);
		
		//get all headers
		Headers headers = res.getHeaders();
		System.out.println("All Headers values ==> "+ headers);
		
	}
}
