package day4;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
/*
explain this statement JSONObject jo = new JSONObject(resbody.asString());
ChatGPT
Sure! The statement JSONObject jo = new JSONObject(resbody.asString()); is used in Java programming, specifically with the org.json library, to create a JSON object from a response body. Here's a breakdown of what this statement does:

resbody.asString(): This part of the statement calls the asString() method on an object named resbody. Typically, resbody is an object representing the body of an HTTP response, such as from a REST API call. The asString() method converts the response body to a string.

For example, if the response body contains JSON data like this:
json
Copy code
{
  "name": "John Doe",
  "age": 30,
  "city": "New York"
}
resbody.asString() would return this JSON data as a plain string:
java
Copy code
String responseString = "{ \"name\": \"John Doe\", \"age\": 30, \"city\": \"New York\" }";
new JSONObject(resbody.asString()): This part of the statement creates a new JSONObject instance by passing the string representation of the JSON data (obtained from resbody.asString()) to the JSONObject constructor. The JSONObject class is part of the org.json library, which is commonly used for parsing and manipulating JSON data in Java.

Given the string representation of the JSON data, the JSONObject constructor parses this string and creates a corresponding JSONObject instance. This instance allows you to interact with the JSON data in a structured way, such as accessing specific fields.
JSONObject jo = new JSONObject(resbody.asString());: Finally, this part of the statement assigns the newly created JSONObject instance to a variable named jo.

After this statement, jo is a JSONObject that contains the parsed JSON data, and you can use various methods provided by the JSONObject class to interact with this data.
Example Usage
Here's an example of how you might use this in a larger context:

java
Copy code
import org.json.JSONObject;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Example {
    public static void main(String[] args) {
        // Perform an HTTP GET request and get the response
        Response resbody = get("https://api.example.com/user");

        // Convert the response body to a string and parse it into a JSONObject
        JSONObject jo = new JSONObject(resbody.asString());

        // Access fields in the JSONObject
        String name = jo.getString("name");
        int age = jo.getInt("age");
        String city = jo.getString("city");

        // Print the extracted fields
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
    }
}
In this example, an HTTP GET request is made to https://api.example.com/user, and the response body is parsed into a JSONObject. The fields name, age, and city are then extracted from the JSON data and printed.

*/


public class ParsingJsonResponseData {

	@Test(priority=1)
	public void testJsonResponse()
	{
		//Approach 1
//		given()
//			.contentType(ContentType.JSON)
//		.when()
//			.get("http://localhost:3000/store")
//		.then()
//			.statusCode(200)
//			.header("Content-Type", "application/json")
//			.body("book[2].title", equalTo("Bhagat singh story"));
		
		//Approach 2
		Response resbody = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
			
		Assert.assertEquals(resbody.getStatusCode(), 200);
		Assert.assertEquals(resbody.getHeader("Content-Type"),"application/json" );
		Assert.assertEquals(resbody.jsonPath().get("book[2].title"), "Bhagat singh story");
	}
	@Test(priority=2)
	public void testJsonResponseBodyData()
	{
		Response resbody = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
	   
		//JSONObject class
		JSONObject jo = new JSONObject(resbody.asString());//converting response to json object type
//		for(int i=0;i<jo.getJSONArray("book").length();i++)
//		{
//			String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
//			System.out.println("Title of book is : "+booktitle);
//		}
		
		//validate book title
		boolean status=false;
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(booktitle.equals("subhash chandra bose story"))
			{
				System.out.println("titles are matched");
				status=true;
				break;
			}
		}
		Assert.assertEquals(status, true);
		
		//validate total price of books
		double totalprice=0.0;
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			totalprice+=Double.parseDouble(price);
		}
		System.out.println(totalprice);
		Assert.assertEquals(totalprice, 371.5);
	}
	
	@Test(priority=3)
	public void testJsonResponseBodyData2()
	{
		Response resbody = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
	   
		//JSONObject class
		JSONObject jo = new JSONObject(resbody.asString());//converting response to json object type
		
		//validate country name of author location
		boolean status=false;
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			String authorlocation = jo.getJSONArray("book").getJSONObject(i).getJSONObject("authorlocation").get("location").toString();
			//System.out.println("Location of Author is : "+ authorlocation);
			if(authorlocation.equals("India"))
			{
				System.out.println("Author Location is matched");
				status=true;
				break;
			}
		}
	}
}
